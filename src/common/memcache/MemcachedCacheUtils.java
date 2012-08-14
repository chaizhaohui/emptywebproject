package common.memcache;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;

/**
 * MemcachedCache工具类
 * @date 2012-03-14
 */
public class MemcachedCacheUtils {
	private static final Logger logger = Logger.getLogger(MemcachedCacheUtils.class);
	private static MemCachedClient client = null;
	private static MemCachedClient sessionClient = null;
	
	/**
	 * 取得Client
	 * @return MemCachedClient
	 */
	public static MemCachedClient getClient(){
		if(client == null){
			synchronized(MemcachedCacheUtils.class){
				if(client == null){
					try{
						client = Util.buildClient();					
					} catch(Exception ex){
						logger.error("MemCachedClient - [getClient] Create memcached client instance error.", ex);
						throw new RuntimeException("Something bad happened while building the MemCachedClient instance." + ex, ex);
					}
				}
			}
		}
		return client;
	}
	
	/**
	 * 取得Session Client
	 * @return
	 */
	public static MemCachedClient getSessionClient(){
		if(sessionClient == null){
			synchronized(MemcachedCacheUtils.class){
				if(sessionClient == null){
					try{
						sessionClient = Util.buildSessionClient();
					} catch(Exception ex){
						logger.error("MemCachedClient - [getSessionClient] Create memcached client instance error.", ex);
						throw new RuntimeException("Something bad happened while building the Session MemCachedClient instance." + ex.getMessage(), ex);
					}
				}
			}
		}
		return sessionClient;
	}
	
	private MemcachedCacheUtils(){}
}
