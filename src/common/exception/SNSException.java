package common.exception;

import org.apache.log4j.Logger;

/**
 * SNS后台管理模块Exception
 * @author lixinglei
 * @date 2012-04-21
 */
public class SNSException extends BasicException {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SNSException.class);
	
	public SNSException(Class<?> clazz, String msg){
		super(clazz,msg);
	}
}
