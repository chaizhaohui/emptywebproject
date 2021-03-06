package common.memcache;

/**
 * Memcached配置文件常量接口
 * @date 2012-03-14
 */
public interface MemcacheConfig {
	
	//数据缓冲池配置
	public static final String DATACACHE_POOLNAME = "memcache.datacache.poolName";
	public static final String DATACACHE_SERVERS = "memcache.datacache.servers";
	public static final String DATACACHE_WEIGHTS = "memcache.datacache.weights";
	public static final String DATACACHE_INITCONN = "memcache.datacache.initConn";
	public static final String DATACACHE_MINCONN = "memcache.datacache.minConn";
	public static final String DATACACHE_MAXCONN = "memcache.datacache.maxConn";
	public static final String DATACACHE_MAXIDLE = "memcache.datacache.maxIdle";
	public static final String DATACACHE_MAINTSLEEP = "memcache.datacache.maintSleep";
	public static final String DATACACHE_NAGLE = "memcache.datacache.nagle";
	public static final String DATACACHE_SOCKETTO = "memcache.datacache.socketTO";
	public static final String DATACACHE_SOCKETCONNECTT = "memcache.datacache.socketConnectT";
	public static final String DATACACHE_COMPRESSENABLE = "memcache.datacache.CompressEnable";
	public static final String DATACACHE_COMPESSTHRESHOLD = "memcache.datacache.CompressThreshold";
	
	//Session共享池配置
	public static final String SESSIONCACHE_POOLNAME = "memcache.sessioncache.poolName";
	public static final String SESSIONCACHE_SERVERS = "memcache.sessioncache.servers";
	public static final String SESSIONCACHE_WEIGHTS = "memcache.sessioncache.weights";
	public static final String SESSIONCACHE_INITCONN = "memcache.sessioncache.initConn";
	public static final String SESSIONCACHE_MINCONN = "memcache.sessioncache.minConn";
	public static final String SESSIONCACHE_MAXCONN = "memcache.sessioncache.maxConn";
	public static final String SESSIONCACHE_MAXIDLE = "memcache.sessioncache.maxIdle";
	public static final String SESSIONCACHE_MAINTSLEEP = "memcache.sessioncache.maintSleep";
	public static final String SESSIONCACHE_NAGLE = "memcache.sessioncache.nagle";
	public static final String SESSIONCACHE_SOCKETTO = "memcache.sessioncache.socketTO";
	public static final String SESSIONCACHE_SOCKETCONNECTT = "memcache.sessioncache.socketConnectT";
	public static final String SESSIONCACHE_COMPRESSENABLE = "memcache.sessioncache.CompressEnable";
	public static final String SESSIONCACHE_COMPESSTHRESHOLD = "memcache.sessioncache.CompressThreshold";
}
