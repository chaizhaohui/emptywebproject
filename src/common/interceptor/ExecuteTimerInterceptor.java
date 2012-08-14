package common.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * Action执行时间拦截器 记录每个Action执行时间
 * @author QiaoGuangqing
 * @date 2012-03-21
 */
public class ExecuteTimerInterceptor extends AbstractInterceptor {
	private static final Logger logger = Logger.getLogger(ExecuteTimerInterceptor.class);
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		long startTime = System.currentTimeMillis();
		String result = invocation.invoke();
		long endTime = System.currentTimeMillis();
		
		if(logger.isInfoEnabled()){
			logger.info(new StringBuffer(12).append("{").append(invocation.getAction().getClass().getName()).append("}[execute time = {").append(endTime - startTime).append("} ms]").toString());
		}
		
		return result;
	}

}
