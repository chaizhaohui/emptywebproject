package common.exception;

import org.apache.log4j.Logger;

import common.utils.StackTraceUtil;

import common.utils.TerminalCodes;

/**
 * 所有自定义异常基类 所有异常都继承此基类
 * @author QiaoGuangqing
 * @date 2012-03-14
 */
public class BasicException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BasicException.class);
	//错误码	默认值:500 未知服务端错误
	private int errorCode = TerminalCodes.UNKNOWN_SERVER_ERROR;
	public BasicException(Class<?> clazz, String msg){
		super(msg);
		logger.warn(clazz.getName() + " : " +msg);
	}
	
	public BasicException(String msg, Throwable t){
		super(msg, t);
		logger.error(msg + " errorCode=" + errorCode + " Cause by:\n" + t.toString() + StackTraceUtil.stackTrace2String(t.getStackTrace()));
	}
	
	public BasicException(String msg, int errorCode, Throwable t){
		super(msg, t);
		this.errorCode = errorCode;
		logger.error(msg + " errorCode=" + errorCode + " Cause by:\n" + t.toString() + StackTraceUtil.stackTrace2String(t.getStackTrace()));
	}
	
	public BasicException(Class<?> clazz, String msg, int errorCode, Throwable t) {
		super(msg, t);
		logger.error(clazz.getName() + ":" +msg + " errorCode=" + errorCode + " Cause by:\n" + t.toString() + StackTraceUtil.stackTrace2String(t.getStackTrace()));
	}
	
	public BasicException(Class<?> clazz, String msg, Throwable t){
		super(msg, t);
		logger.error(clazz.getName() + " : " + msg + " Cause by:\n" + t.toString() + stackTrace2String(t.getStackTrace()));
	}

	public BasicException(String msg, int errorCode){
		super(msg);
		this.errorCode = errorCode;
		logger.warn(msg + " errorCode=" + errorCode);
	}
	
	
	public BasicException(Class<?> clazz, String msg, int errorCode){
		super(msg);
		this.errorCode = errorCode;
		logger.error(clazz.getName() + ":" + msg + " errorCode=" + errorCode);
	}
	
	public BasicException(String msg){
		super(msg);
	}
	public static String stackTrace2String(StackTraceElement []stackTrace){
		if(stackTrace != null){
			StringBuffer sb = new StringBuffer(32);
			for(int i=0;i<stackTrace.length;i++){
				sb.append("\n").append(stackTrace[i].toString());
			}
			return sb.toString();
		}
		return null;
	}
	public int getErrorCode(){
		return this.errorCode;
	}
}
