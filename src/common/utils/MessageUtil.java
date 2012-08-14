package common.utils;


import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 服务端将错误码转换为错误文本工具类
 * @date 2012-04-06
 */
public class MessageUtil {
	private static final Logger logger = Logger.getLogger(MessageUtil.class);
	
	//配置文件路径
	private static final String PATH = Thread.currentThread().getContextClassLoader().getResource("message.properties").getFile();
	
	private static Properties properties = new Properties();
	
	static {
		try{
			logger.info("初始化message.properties配置项目");
			properties.load(new FileInputStream(PATH));
			logger.info("初始化message.properties配置项目成功");
		} catch(Exception ex){
			logger.error("读取配置文件 "+PATH+" 错误. Cause by:" + ex.getMessage());
		}
	}
	
	/**
	 * 依据Key取得配置内容
	 * @param key
	 * @return
	 */
	public static String getProperty(int code){
		String msgText = "";
		try {
			msgText = new String(General.convertNullToEmpty(properties.getProperty("MSG_" + code)).getBytes("Latin1"), "UTF8");
			if(General.isEmpty(msgText))
				return new String(properties.getProperty("MSG_000").getBytes("Latin1"), "UTF8");
		} catch (UnsupportedEncodingException ex) {
			logger.error("read message.properties error. Cause by:" + ex.toString());
		}
		return msgText;
	}
}
