package common.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.exception.BasicException;
import common.interceptor.HeaderIF;
import common.utils.General;
import common.utils.MessageUtil;
import common.utils.ParamType;
import common.utils.TerminalCodes;
import common.utils.ParamType.PARAM_TYPE;

/**
 * 所有Action基类 封装了RequestHeader对象以及捕捉异常 所有Action继承此基类
 * 
 * @date 2012-03-15
 */
public abstract class InterBasicAction extends ActionSupport implements ActionI {
	private static final Logger logger = Logger.getLogger(InterBasicAction.class);

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;

	@ParamType(PARAM_TYPE.OUT)
	public int resultCode = 200;
	@ParamType(PARAM_TYPE.OUT)
	public String resultMsg = "";

	@ParamType(PARAM_TYPE.OUT)
	private BigDecimal discussCount; // 评论提醒数
	@ParamType(PARAM_TYPE.OUT)
	private BigDecimal friendCount; // 关注提醒数
	@ParamType(PARAM_TYPE.OUT)
	private BigDecimal replayCount; // 回复提醒数

	public String execute() {
		// 初始化Header参数
		initRequestHeader();

		// 调用主方法
		try {
			return doResult(doAction());
		} catch (BasicException ex) {
			this.setResultCode(ex.getErrorCode());
			this.setResultMsg(MessageUtil.getProperty(ex.getErrorCode()));
			this.setErrorMsg(ex.getErrorCode(), this.getClass());
			return null;
		} catch (Exception ex) {
			new BasicException(ex.getMessage(), ex);
			this.setResultCode(TerminalCodes.UNKNOWN_SERVER_ERROR);
			this.setResultMsg(MessageUtil
					.getProperty(TerminalCodes.UNKNOWN_SERVER_ERROR));
			this.setErrorMsg(TerminalCodes.UNKNOWN_SERVER_ERROR, this
					.getClass());
			return null;
		}
	}

	public String doResult(String def) {
		if (AUTO.equals(def)) {
			String resultType = General.convertNullToEmpty(this.request
					.getHeader(HeaderIF.RESULT_TYPE));

			if ("json".equalsIgnoreCase(resultType))
				return "jsonResult";
			else if ("xml".equalsIgnoreCase(resultType))
				return "xmlResult";
			else {
				return "xmlResult";
			}
		} else
			return def;
	}

	

	

	

	/**
	 * 获取Request Header中的所有需要的内容并封装为RequestHeader对象
	 */
	private void initRequestHeader() {
		ActionContext actionContext = ActionContext.getContext();
		this.request = (HttpServletRequest) actionContext
				.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
		this.response = (HttpServletResponse) actionContext
				.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
	}

	private void setErrorMsg(int errorCode, Class<?> clazz) {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String result = General.convertNullToEmpty(request
				.getHeader(HeaderIF.RESULT_TYPE));
		if (result.equalsIgnoreCase("json")) {
			StringBuffer jsonStr = new StringBuffer(32);
			jsonStr.append("{").append("\"resultCode\":").append(errorCode)
					.append(",\"resultMsg\":\"").append(
							MessageUtil.getProperty(errorCode)).append("\"}");
			response.setHeader("Content-Type", "text/html");
			PrintWriter printWriter;
			try {
				printWriter = response.getWriter();
				printWriter.println(jsonStr.toString());
				printWriter.flush();
				printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String actionName = clazz.getSimpleName() + "Rsp";
			StringBuffer body = new StringBuffer(32);
			body.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			body.append("<").append(actionName).append(">");
			body.append("<resultCode>").append(errorCode).append(
					"</resultCode>");
			body.append("<resultMsg>").append(
					MessageUtil.getProperty(errorCode)).append("</resultMsg>");
			body.append("</").append(actionName).append(">");

			response.setHeader("Content-Type", "application/xml");
			ByteArrayOutputStream baseOut = new ByteArrayOutputStream(512);
			OutputStream outputStream = baseOut;

			String acceptGzip = request.getHeader("Encoding-Type");
			if ("gzip".equalsIgnoreCase(acceptGzip)) {
				response.setHeader("Encoding-Type", "gzip");
				try {
					outputStream = new GZIPOutputStream(baseOut);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			byte[] byteXml;
			try {
				byteXml = body.toString().getBytes("UTF-8");
				outputStream.write(byteXml);
				outputStream.close();
				response.setHeader("Content-Length", String.valueOf(baseOut
						.size()));
				response.setHeader("Or-Content-Length", String
						.valueOf(byteXml.length));
				response.setCharacterEncoding("UTF-8");
				baseOut.writeTo(response.getOutputStream());
				baseOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public HttpServletResponse getResponse() {
		return this.response;
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public BigDecimal getDiscussCount() {
		return discussCount;
	}

	public void setDiscussCount(BigDecimal discussCount) {
		this.discussCount = discussCount;
	}

	public BigDecimal getFriendCount() {
		return friendCount;
	}

	public void setFriendCount(BigDecimal friendCount) {
		this.friendCount = friendCount;
	}

	public BigDecimal getReplayCount() {
		return replayCount;
	}

	public void setReplayCount(BigDecimal replayCount) {
		this.replayCount = replayCount;
	}

	public abstract String doAction() throws Exception;

	public String doActionFor11() throws Exception {
		return null;
	}
	public String doActionFor12() throws Exception {
		return null;
	}

	/**
	 * 判断Action中是否包含指定方法
	 * 
	 * @param obj
	 * @param methodName
	 * @return
	 */
	private static final boolean haveMethod(Object obj, String methodName) {
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName))
				return true;
		}
		return false;
	}
}
