package common.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import common.utils.General;
import common.utils.MessageUtil;
/**
 * 鉴权拦截器 所有接口请求都要通过此拦截器
 * 1.验证DeviceNum是否存在 不存在则自动建立
 * 2.自动注册并绑定用户
 * @author QiaoGuangqing
 * @date 2012-03-21
 */
public class AuthenticaitonInterceptor extends AbstractInterceptor {
	private static final Logger logger = Logger.getLogger(AuthenticaitonInterceptor.class);
	private static final long serialVersionUID = 1L;

	/**
	 * 拦截器主方法
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//验证Header中存在X-Device-Id
		String cpid = request.getHeader("X-Cp-Id");
		String cpPwd = request.getHeader("X-Cp-Hash");
	
		// 先生成 MD5（CPID + password）
        // 再 Base64.encode（MD5）
        //String cpAuth = cpAccount + cpBean.getPassword();
        
        //cpAuth = Base64.encode(CipherOperateUtil.MD5Encrypt(cpAuth));
        //cpAuth=CipherOperateUtil.encryptBySHA256(cpAuth);
		
		return invocation.invoke();
		
	}
	
	private void setErrorMsg(int errorCode, ActionInvocation invocation) throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		String result = General.convertNullToEmpty(request.getHeader(HeaderIF.RESULT_TYPE));
		if(result.equalsIgnoreCase("json")){
			StringBuffer jsonStr = new StringBuffer(32);
			jsonStr.append("{").append("\"resultCode\":").append(errorCode).append(",\"resultMsg\":\"").append(MessageUtil.getProperty(errorCode)).append("\"}");
			response.setHeader("Content-Type", "text/html");
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonStr.toString());
			printWriter.flush();
			printWriter.close();
		}else{
			String actionName = invocation.getAction().getClass().getSimpleName() + "Rsp";
			StringBuffer body = new StringBuffer(32);
			body.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			body.append("<").append(actionName).append(">");
			body.append("<resultCode>").append(errorCode).append("</resultCode>");
			body.append("<resultMsg>").append(MessageUtil.getProperty(errorCode)).append("</resultMsg>");
			body.append("</").append(actionName).append(">");
			
			response.setHeader("Content-Type", "application/xml");
			ByteArrayOutputStream baseOut = new ByteArrayOutputStream(512);
			OutputStream outputStream = baseOut;
			
			String acceptGzip = request.getHeader("Encoding-Type");
			if("gzip".equalsIgnoreCase(acceptGzip)){
				response.setHeader("Encoding-Type", "gzip");
				outputStream = new GZIPOutputStream(baseOut);
			}
			
			byte []byteXml = body.toString().getBytes("UTF-8");
			outputStream.write(byteXml);
			outputStream.close();
			
			response.setHeader("Content-Length", String.valueOf(baseOut.size()));
			response.setHeader("Or-Content-Length", String.valueOf(byteXml.length));
			response.setCharacterEncoding("UTF-8");
			baseOut.writeTo(response.getOutputStream());
			baseOut.close();
		}
	}
}
