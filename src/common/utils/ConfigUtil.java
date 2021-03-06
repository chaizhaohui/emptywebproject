package common.utils;


import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 系统配置文件读取类
 * 用于读取系统配置
 * @author 乔广庆
 * @date 2012-01-10
 */
public class ConfigUtil {
	private static final Logger logger = Logger.getLogger(ConfigUtil.class);
	
	/**	运行实例号 集群配置时每个实例配不同的实例号 **/
	public static final String SYSTEM_NAME = "system.name";
	
	/** 接口版本号 **/
	public static final String API_VERSION = "system.service.api.version";
	
	//Memcache数据缓冲池配置项目
	public static final String MEM_DATACACHE_POOLNAME = "memcache.datacache.poolName";
	public static final String MEM_DATACACHE_SERVERS = "memcache.datacache.servers";
	public static final String MEM_DATACACHE_WEIGHTS = "memcache.datacache.weights";
	public static final String MEM_DATACACHE_INITCONN = "memcache.datacache.initConn";
	public static final String MEM_DATACACHE_MINCONN = "memcache.datacache.minConn";
	public static final String MEM_DATACACHE_MAXCONN = "memcache.datacache.maxConn";
	public static final String MEM_DATACACHE_MAXIDLE = "memcache.datacache.maxIdle";
	public static final String MEM_DATACACHE_MAINTSLEEP = "memcache.datacache.maintSleep";
	public static final String MEM_DATACACHE_NAGLE = "memcache.datacache.nagle";
	public static final String MEM_DATACACHE_SOCKETTO = "memcache.datacache.socketTO";
	public static final String MEM_DATACACHE_SOCKETCONNECTT = "memcache.datacache.socketConnectT";
	public static final String MEM_DATACACHE_COMPRESSENALBED = "memcache.datacache.CompressEnable";
	public static final String MEM_DATACACHE_COMPRESSTHRESHOLD = "memcache.datacache.CompressThreshold";
	public static final String MEMCACHE_ISOPEN = "memcache.isopen";
	
	/** 用户信息存放在Session中的Key **/
	public static final String SESSION_USER_KEY = "session.userKey";
	/** 设备信息存放在Session中的Key **/
	public static final String SESSION_DEVICE_KEY = "session.deviceKey";
	
    /**
     * session存放验证码的key
     */
    public static String SESSION_VERIFICODE = "session.verificode";
	
	/** 图片URL地址前缀 */
	public static final String IMAGES_URL_PREFIX = "url.images.prefix";
	/** 图片本地存放路径前缀 */
	public static final String IMAGES_LOCALPATH_PREFIX = "localpath.images.prefix";
	/** 用户头像图片URL地址前缀 */
	public static final String HEAD_IMG_URL_PREFIX = "url.headimg.prefix";
	/** 用户头像图片本地存放路径前缀 */
	public static final String HEAD_IMG_LOCALPATH_PREFIX = "localpath.headimg.prefix";
	/** 书籍下载URL地址前缀 */
	public static final String BOOKS_URL_PREFIX = "url.books.prefix";
	/** 书籍本地存放路径前缀 */
	public static final String BOOKS_LOCALPATH_PREFIX = "localpath.books.prefix";
	/** 音频书籍下载URL地址前缀 */
	public static final String AUDIOBOOKS_URL_PREFIX = "url.audiobooks.prefix";
	/** 音频书籍本地存放路径前缀 */
	public static final String AUDIOBOOKS_LOCALPATH_PREFIX = "localpath.audiobooks.prefix";
	/** 客户端皮肤下载URL地址前缀 */
	public static final String CLIENTSKIN_URL_PREFIX = "url.clientskin.prefix";
	/** 客户端皮肤地存放路径前缀 */
	public static final String CLIENTSKIN_LOCALPATH_PREFIX = "localpath.clientskin.prefix";
	/** 客户端下载URL地址前缀 */
	public static final String CLIENT_URL_PREFIX = "url.client.prefix";
	/** 客户端地存放路径前缀 */
	public static final String CLIENT_LOCALPATH_PREFIX = "localpath.client.prefix";
	/** 专题图片下载URL地址前缀 */
	public static final String PROMOTION_URL_PREFIX = "url.promotion.prefix";
	/** 专题图片本地存放路径前缀 */
	public static final String PROMOTION_LOCALPATH_PREFIX = "localpath.promotion.prefix";
	/** 书籍封面下载URL地址前缀 */
	public static final String BOOKCOVER_URL_PREFIX_SMALL = "url.bookcover.prefix.small";
	/** 书籍封面下载URL地址前缀 */
	public static final String BOOKCOVER_URL_PREFIX_BIG = "url.bookcover.prefix.big";
	/** 书籍封面本地存放路径前缀 */
	public static final String BOOKCOVER_LOCALPATH_PREFIX_SMALL = "localpath.bookcover.prefix.small";
	/** 书籍封面本地存放路径前缀 */
	public static final String BOOKCOVER_LOCALPATH_PREFIX_BIG = "localpath.bookcover.prefix.big";
	/** 书籍封面本地存放路径前缀 */
	public static final String DES_KEY = "key.des";
	/**drm请求地址*/
	public static final String DRM_URL = "drm.url";
	/**ftp地址*/
	public static final String FTP_URL = "ftp.url";
	/** 下载书籍跳转URL */
	public static final String URL_BOOK_DOWNLOAD = "url.book.download";
	
	/** 下载听书章节跳转URL */
	public static final String URL_CHAPTER_DOWNLOAD = "url.chapter.download";
	/**ftp用户名*/
	public static final String FTP_USERNAME = "ftp.username";
	/**ftp密码*/
	public static final String FTP_PASSWORD = "ftp.password";
	/**ftp端口号*/
	public static final String FTP_PORT = "ftp.port";
	/**drm打包完成返回消息地址*/
	public static final String DRM_RESPONSEURI = "drm.responseuri";
	/**drm 打包文件生成路径*/
	public static final String DRM_XML_FILE_URL = "drm.xml.file.url";
	/**打包文件存放路径*/
	public static final String HOME_URL  = "home.url";
	/**ftp根目录*/
	public static final String FTP_ROOT_URL  = "ftp.root.url";
	/**drm图书存放目录*/
	public static final String BASE_PATH = "base.path"; 
	/**#drm打包完成图书存放地址**/
	public static final String LOCAHOST_PATH = "locahost.path"; 
	/**#校验文件是否存在，临时文件存放地址*/
	public static final String TEMPORARY_CATALOG = "temporary.catalog";
	/**系统路径分隔符*/
	public static final String SYSTEM_SEPARATOR= "system.separator"; 
	/**拼装drm请求地址替换路径*/
	public static final String REPLACE_PATH= "replace.path"; 
	/**oracle与mysql切换标示符1为oracle，0为mysql*/
	public static final String DATABASE_TYPE = "database.type";
	
	/** 客户端版本 */
	public static final String CLIENT_AGENT= "client.agent";
	/** 客户端版本密钥 */
	public static final String CLIENT_KEY = "client.key";
	/** AES加密密钥 */
	public static final String AES_KEY = "key.aes";
	
	/** drm请求内容许可证路径 */
	public static final String DRM_GETDRMTICKET = "drm.getDrmTicket";
	
	/** drm请求激活码路径 */
	public static final String DRM_REGCODEPATH = "drm.regCodePath";
	
	/**解密测试账号*/
	public static final String DRM_USERID = "drm.userid";
	/**获取激活码地址,访问services下载接口.*/
	public static final String DRM_SERVICESKEY = "drm.serviceskey";
	/**设备id*/
	public static final String DEVICENUM = "devicenum";
	
	//配置文件路径
	private static final String PATH = Thread.currentThread().getContextClassLoader().getResource("config.properties").getFile();
	
	private static Properties properties = new Properties();
	
	static {
		try{
			logger.info("初始化系统配置项目");
			properties.load(new FileInputStream(PATH));
			logger.info("初始化系统配置项目成功");
		} catch(Exception ex){
			logger.error("读取系统配置文件 "+PATH+" 错误. Cause by:" + ex.getMessage());
		}
	}
	
	/**
	 * 依据Key取得配置内容
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
	public static Properties getProperties(){
		return properties;
	}
	//判断memchache是否开启
	public static boolean isMemcacheOpen(){
		if(ConfigUtil.getProperty(ConfigUtil.MEMCACHE_ISOPEN).equals("1")){
			return true;
		}
		return false;
	}
}
