package common.utils;
/**
 * 异常堆栈信息工具类
 * @author QiaoGuangqing
 * @date 2012-04-24
 */
public class StackTraceUtil {
	
	/**
	 * 异常堆栈信息转换为String
	 * @param stackTrace
	 * @return
	 */
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
