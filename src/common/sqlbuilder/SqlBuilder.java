package common.sqlbuilder;



import java.io.Reader;

import org.apache.log4j.Logger;


import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import common.utils.ConfigUtil;

public class SqlBuilder {
	private static final Logger logger = Logger.getLogger(SqlBuilder.class);
	
	private static SqlMapClient sqlMapClient = null;
	
	public static SqlMapClient getSqlMapClient(){
		if(sqlMapClient == null){
			synchronized(SqlBuilder.class){
				if(sqlMapClient == null){
					try{
						String jdbcType = ConfigUtil.getProperty(ConfigUtil.DATABASE_TYPE);
						Reader reader = null;
						if("0".equals(jdbcType))
						{
							reader =Resources.getResourceAsReader("MysqlSqlMapConfig.xml");
						}
						else
						{
							reader =Resources.getResourceAsReader("OracleSqlMapConfig.xml");
						}
						sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
						reader.close();
					} catch(Exception ex){
						logger.error("创建数据库连接池失败. Cause by:" + ex.getMessage());
						throw new RuntimeException(ex);
					}
				}
			}
		}
		return sqlMapClient;
	}
}
