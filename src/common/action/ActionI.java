package common.action;

/**
 * 所有Action接口 BasicAction将实现此接口
 * 
 * @date 2012-03-14
 */
public interface ActionI {
	
	/**
	 * doAction返回auto时将由Action基类根据requestHeader.X-ResultType参数来确定返回方式并自动返回
	 * doAction返回其他字符串时将根据返回值直接调用
	 */
	public static final String AUTO = "auto";
	
	/**
	 * Action主方法 处理参数后调用BO层处理业务逻辑并封装返回值
	 * @return
	 * @throws Exception
	 */
	public String doAction() throws Exception;
	
	/**
	 * Action主方法 适配客户端版本号为1.1开头的客户端请求
	 * @return
	 * @throws Exception
	 */
	public String doActionFor11() throws Exception;
	
	/**
	 * 构造返回结果 确定返回类型
	 * @param def
	 * @return
	 */
	public String doResult(String def);
	
	/**
	 * 默认方法 BasicAction将调用此方法开始业务处理
	 * @return
	 */
	public String execute();
}
