package common.dto;


import java.math.BigDecimal;

import common.utils.ConfigUtil;
import common.utils.General;


/**
 * 用户表DTO
 * @author QiaoGuangqing
 * @date 2012-04-06
 */
public class UserDTO extends BasicDTO {
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}

