package common.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;


/**
 * 所有Service通用基类 封装了当前请求的Request Response Session对象 所有Service继承此基类以获得该对象
 * @date 2012-03-14
 */
public class BasicService {
	
	
	protected BasicService(){
		init();
	}
	
	private void init() {
		
	}
	//add by lixinglei at 2012-06-01 for DAO singleton end (line55-line136)//
	
	/**
	 * 获取当前请求的Session
	 * @return
	 */
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	
	/**
	 * 获取当前请求的Request
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 获取当前请求的Response
	 * @return
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	
}
