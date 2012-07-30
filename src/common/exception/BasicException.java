package common.exception;

import org.apache.log4j.Logger;

/**
 * 所有自定义异常基类 所有异常都继承此基类
 * @author QiaoGuangqing
 * @date 2012-03-14
 */
public class BasicException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(BasicException.class);
	
	public BasicException(Class<?> clazz, String msg){
		super(msg);
		logger.warn(clazz.getName() + " : " +msg);
	}
	
	public BasicException(Class<?> clazz, String msg, Throwable t){
		super(msg, t);
		logger.error(clazz.getName() + " : " + msg + " Cause by:\n" + t.toString() + stackTrace2String(t.getStackTrace()));
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
}
