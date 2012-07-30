package common.interceptor;
/**
 * RequestHeader参数常量接口
 * @author QiaoGuangqing
 * @date 2012-04-06
 */
public interface HeaderIF {
	
	/** 客户端DeviceNum[request必填] **/
	public static final String DEVICE_NUM = "X-Device-Id";
	
	/** 客户端设备类型与软件版本号[request必填] e.g. T61/1.0.0.1 **/
	public static final String CLIENT_AGENT = "X-Client-Agent";
	
	/** 接口版本号[request必填][response必填] e.g. 1.0.0 **/
	public static final String API_VERSION = "X-Api-Version";
	
	/** Header中存放的SessionId[request选填][response必填] **/
	public static final String SESSION_ID = "X-Session-Id";
	
	/** Header中存放的UserId[request选填][response必填] **/
	public static final String USER_ID = "X-User-Id";
	
	/** Header中存放的UserName[request选填] **/
	public static final String USER_NAME = "X-User-Account";
	
	/** Header中存放的UserPwd[request选填] **/
	public static final String USER_PWD = "X-User-Hash";
	
	/** Header中存放的服务器时间[response必填] **/
	public static final String TIMESTAMP = "X-Timestamp";
	
	/** Header中存放的返回值类型[request选填] e.g. json or xml.default is xml **/
	public static final String RESULT_TYPE = "X-ResultType";
}
