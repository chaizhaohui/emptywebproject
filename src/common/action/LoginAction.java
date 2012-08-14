package common.action;

import java.io.IOException;


import com.opensymphony.xwork2.ActionSupport;
import common.utils.General;

/**
 * 用户登录Action
 * @date 2012-04-18
 */
public class LoginAction extends AppBasicAction {
	
	private String userName;	//用户名
	private String userPwd;		//用户密码
	
	private String result;		//登录动作返回结果 成功时为success
	
	private String verifiCode;  //验证码

	/**
	 * 跳转到用户登录页面
	 * @return
	 */
	public String toLogin(){
		return ActionSupport.SUCCESS;
	}
	

	public String getResult() {
		return result;
	}
	
	public String getVerifiCode() {
		return verifiCode;
	}

	public void setVerifiCode(String verifiCode) {
		this.verifiCode = verifiCode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
}
