package common.action;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import common.utils.General;

/**
 * 所有Action基类 定义Action一般常用方法
 * 
 * @author 乔广庆
 * @date 2012-02-06
 */
public class BasicAction {
	public static final String MARKER = "marker";
	public static final String JSON = "json";
	public static final String REDIRECT = "redirect";
	public static final String REDIRECETACTION = "redirectAction";
	public static final String CHAINACTION = "chain";
	public String action;

	public String returnUrl;
	public String refUrl;
	public String prvUrl;
	//配置页面的路径
	private String basePath;
	
	/**
	 * 获取当前Request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取当前Response对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取当前HttpSession对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return this.getRequest().getSession();
	}

	

	/**
	 * 设置系统消息返回前台显示
	 * 
	 * @param msg
	 */
	public void setMessage(String msg) {
		this.getSession().setAttribute("msg", msg);
	}

	/**
	 * 返回上一页并提示消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void goBack(String msg) throws IOException {
		StringBuffer sb = new StringBuffer(16);
		sb.append("<script>");
		if (General.isNotEmpty(msg))
			sb.append("alert('").append(msg).append("');");
		sb.append("history.go(-1);");
		sb.append("</script>");
		HttpServletResponse response = getResponse();
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Expires", "0");
		ServletOutputStream out = response.getOutputStream();
		out.write(sb.toString().getBytes("utf-8"));
		out.flush();
		out.close();
	}

	/**
	 * 返回上一页并提示消息
	 * 
	 * @param msg
	 * @throws IOException
	 */
	public void returnXml(String msg, int code) throws IOException {
		StringBuffer sb = new StringBuffer(16);
		sb.append("<Response>");
		sb.append("<code>");
		sb.append(code);
		sb.append("</code>");
		sb.append("<msg>");
		sb.append(msg);
		sb.append("</msg>");
		sb.append("</Response>");
		HttpServletResponse response = getResponse();
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Expires", "0");
		ServletOutputStream out = response.getOutputStream();
		out.write(sb.toString().getBytes("utf-8"));
		out.flush();
		out.close();
	}

	/**
	 * 获取当前页面URL（带参数）
	 * 
	 * @return
	 */
	public String getRefUrl() {
		if (General.isNotEmpty(refUrl)) {
			return this.refUrl;
		}

		String queryStr = this.getRequest().getQueryString();
		if (General.isNotEmpty(queryStr))
			this.refUrl = this.getRequest().getRequestURI() + "?" + queryStr;
		else
			this.refUrl = this.getRequest().getRequestURI();

		return this.refUrl;

	}

	/**
	 * 获取当前页面URL（带参数）
	 * 
	 * @return
	 */
	public String getPrvUrl() {
		if (General.isNotEmpty(prvUrl)) {
			return this.prvUrl;
		}

		String queryStr = this.getRequest().getQueryString();
		if (General.isNotEmpty(queryStr))
			this.prvUrl = this.getRequest().getRequestURI() + "?" + queryStr;
		else
			this.prvUrl = this.getRequest().getRequestURI();

  
		return this.prvUrl;
	}



	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}

	public void setPrvUrl(String prvUrl) {
		this.prvUrl = prvUrl;
	}

	public String getPath() {
		String path = this.getRequest().getContextPath();
		return path;
	}

	public String getBasePath() {
		String basePath = this.getRequest().getScheme()+"://"+this.getRequest().getServerName()+":"+this.getRequest().getServerPort()+this.getRequest().getContextPath()+"/";
		return basePath;
	}
}
