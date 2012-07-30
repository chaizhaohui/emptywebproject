package common.dto;


import java.math.BigDecimal;

import common.utils.ConfigUtil;
import common.utils.General;


/**
 * 用户表DTO
 * @author QiaoGuangqing
 * @date 2012-04-06
 */
public class UsersDTO extends BasicDTO {
	private static final long serialVersionUID = 1L;
	
	public static final String REG_TYPE_WEB = "web";			//用户注册方式-网站注册
	public static final String REG_TYPE_CLIENT = "client";		//用户注册方式-客户端注册
	public static final String REG_TYPE_ADMIN = "admin";		//用户注册方式-管理员添加
	public static final String REG_TYPE_OTHER = "other";		//用户注册方式-其他方式
	public static final String STATUS_NOACTIVE = "noactive";	//用户状态-未激活
	public static final String STATUS_ACTIVE = "active";		//用户状态-已激活
	public static final String STATUS_LOCKED = "locked";		//用户状态-已锁定
	
	private String mobile;										//手机号码 可作为登录名 注册时不能重复
	private String email;										//用户Email 可作为登录名 注册时不能重复
	private String userPwd;										//用户加密后的密码
	private String regType = "client";							//用户注册方式[必填] 网站注册:web 客户端注册:client 管理员添加:admin 其他:other 默认为client
	private String source;										//用户来源
	private String nickName;									//用户昵称
	private String readBookDesc;								//正在阅读
	private String status = "noactive";							//用户状态[必填] 未激活:noactive 已激活:active 已锁定:locked 默认为noactive
	private Integer deviceCount = 0;							//已关联（绑定）客户端数[必填] 默认为0 
	private Long addTime;										//注册时间[必填]
	private String headImgUrl;									//个人头像URL
	private String headImgLocalPath;							//个人头像本地位置
	private Long lastActTime;									//最后一次访问时间[必填]
	private Integer lastDeviceId;								//最后一次访问设备ID 外键[必填]
	private String isRecommend = "0";							//0:否 1:是 默认为0
	private BigDecimal recommendSort = new BigDecimal(1000);	//推荐排序 默认为1000
	private Long recommendTime;									//推荐时间
	
	/**
	 * 获取用户头像图片URL的全路径
	 * @return
	 */
	public String getFullHeadImgUrl(){
		return General.isEmpty(this.headImgUrl)?"":General.convertNullToEmpty(ConfigUtil.getProperty(ConfigUtil.HEAD_IMG_URL_PREFIX)) + this.headImgUrl;
	}
	/**
	 * 获取用户头像图片本地存放的全路径
	 * @return
	 */
	public String getFullHeadImgLocalPath(){
		return General.isEmpty(this.headImgLocalPath)?"":General.convertNullToEmpty(ConfigUtil.getProperty(ConfigUtil.HEAD_IMG_LOCALPATH_PREFIX)) + this.headImgLocalPath;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getReadBookDesc() {
		return readBookDesc;
	}
	public void setReadBookDesc(String readBookDesc) {
		this.readBookDesc = readBookDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getDeviceCount() {
		return deviceCount;
	}
	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}
	public Long getAddTime() {
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getHeadImgLocalPath() {
		return headImgLocalPath;
	}
	public void setHeadImgLocalPath(String headImgLocalPath) {
		this.headImgLocalPath = headImgLocalPath;
	}
	public Long getLastActTime() {
		return lastActTime;
	}
	public void setLastActTime(Long lastActTime) {
		this.lastActTime = lastActTime;
	}
	public Integer getLastDeviceId() {
		return lastDeviceId;
	}
	public void setLastDeviceId(Integer lastDeviceId) {
		this.lastDeviceId = lastDeviceId;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public BigDecimal getRecommendSort() {
		return recommendSort;
	}
	public void setRecommendSort(BigDecimal recommendSort) {
		this.recommendSort = recommendSort;
	}
	public Long getRecommendTime() {
		return recommendTime;
	}
	public void setRecommendTime(Long recommendTime) {
		this.recommendTime = recommendTime;
	}
}

