package common.memcache;


import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import common.utils.ConfigUtil;

/**
 * Memcached工具类
 * @date 2012-03-14
 */
public class Util {
	
	/**
	 * DataCache Client创建方法 只允许MemcacheCacheUtils调用 其他方法请勿调用
	 * @return MemCachedClient
	 */
	public static MemCachedClient buildClient() throws Exception{
		SockIOPool pool = null;
		MemCachedClient client = null;
		
		pool = SockIOPool.getInstance(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_POOLNAME));
		pool.setServers(getServers(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_SERVERS)));
		pool.setInitConn(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_INITCONN)));
		pool.setMinConn(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_MINCONN)));
		pool.setMaxConn(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_MAXCONN)));
		pool.setMaxIdle(Long.parseLong(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_MAXIDLE)));
		pool.setMaintSleep(Long.parseLong(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_MAINTSLEEP)));
		pool.setSocketTO(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_SOCKETTO)));
		pool.setNagle(Boolean.getBoolean(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_NAGLE)));
		pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
		pool.setMaxBusyTime(1000 * 60 * 5);
		pool.setAliveCheck(false);
		pool.initialize();
		
		
		client = new MemCachedClient();
		client.setPoolName(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_POOLNAME));
		client.setCompressEnable(Boolean.getBoolean(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_COMPRESSENABLE)));
		client.setCompressThreshold(Long.parseLong(ConfigUtil.getProperty(MemcacheConfig.DATACACHE_COMPESSTHRESHOLD)));
		return client;
	}
	
	/**
	 * Session Share Client创建方法 只允许MemcacheCacheUtils调用 其他方法请勿调用
	 * @return MemCachedClient
	 */
	public static MemCachedClient buildSessionClient() throws Exception{
		SockIOPool sessionPool = null;
		MemCachedClient sessionClient = null;
		
		sessionPool = SockIOPool.getInstance(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_POOLNAME));
		sessionPool.setServers(getServers(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_SERVERS)));
		sessionPool.setInitConn(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_INITCONN)));
		sessionPool.setMinConn(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_MINCONN)));
		sessionPool.setMaxConn(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_MAXCONN)));
		sessionPool.setMaxIdle(Long.parseLong(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_MAXIDLE)));
		sessionPool.setMaintSleep(Long.parseLong(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_MAINTSLEEP)));
		sessionPool.setSocketTO(Integer.parseInt(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_SOCKETTO)));
		sessionPool.setNagle(Boolean.getBoolean(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_NAGLE)));
		sessionPool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
		sessionPool.setMaxBusyTime(1000 * 60 * 5);
		sessionPool.setAliveCheck(false);
		sessionPool.initialize();
		
		sessionClient = new MemCachedClient();
		sessionClient.setPoolName(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_POOLNAME));
		sessionClient.setCompressEnable(Boolean.getBoolean(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_COMPRESSENABLE)));
		sessionClient.setCompressThreshold(Long.parseLong(ConfigUtil.getProperty(MemcacheConfig.SESSIONCACHE_COMPESSTHRESHOLD)));
		return sessionClient;
	}
	
	public static String[] getServers(String servers){
		return servers.trim().split(";");
	}
	
	/**
	 * 私有构造函数 防止实例化
	 */
	private Util(){}
}
