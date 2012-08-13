package common.dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.dto.BasicDTO;
import common.memcache.MemcachedCacheUtils;
import common.sqlbuilder.SqlBuilder;
import common.utils.ConfigUtil;


/**
 * 所有Dao基类 创建新Dao需要继承此基类 定义了基本的CRUD操作
 * @author QiaoGuangqing
 * @param <T>
 * @date 2012-03-15
 */
public abstract class BasicDAO<T extends BasicDTO> {
	
	private static final String DELIMITER = "$";		//缓存Key ClassName与ID之间分隔符
	
	private Class<T> entityClass;						//运行时实体Bean实例
	private String prefix = "";							//sqlMap中namespace名称 与实体Bean名称一致
	private String className = "";						//class全名称 用于构造缓存Key
	
	/**
	 * 依据表主键ID获取全部填充实体Bean
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public T getById(Integer id) throws SQLException{
		if(id == null)
			return null;
		T t = null;
		if(isMemcacheOpen()){
			//构造缓存中的Key cn.con.twsm.portal.dto.ClientVersionDTO$2002
			String key = new StringBuffer(16).append(this.className).append(DELIMITER).append(id).toString();
			
			//先去缓存中查询Key 查询不到则使用数据库进行查询并将结果放入缓存
			t = (T)MemcachedCacheUtils.getClient().get(key);
			if(t == null){
				t = (T)SqlBuilder.getSqlMapClient().queryForObject(prefix + ".getById", id);
				if(t != null)
					MemcachedCacheUtils.getClient().set(key, t);
			}
			
			key = null;
		}else{
			t = (T)SqlBuilder.getSqlMapClient().queryForObject(prefix + ".getById", id);
		}
		return t;
	}
	
	/**
	 * 依据表主键id List批量查询全部填充实体Bean
	 * @param idList
	 * @return
	 * @throws SQLException
	 */
	public List<T> getById(List<Integer> idList) throws SQLException{
		//如果传入List为空 直接返回null
		if(idList == null || idList.size() < 1)
			return null;
		
		List<T> resultList = new ArrayList<T>();
		
		//循环逐个查询 非空则加入结果集
		for(int i=0;i<idList.size();i++){
			T t = getById(idList.get(i));
			if(t != null)
				resultList.add(t);
		}
		
		//判断结果集为空直接返回null 否则返回结果集
		if(resultList.size() < 1)
			return null;
		else
			return resultList;
	}
	
	/**
	 * 新增记录
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public Object insert(T t) throws SQLException{
		return SqlBuilder.getSqlMapClient().insert(prefix + ".insert", t);
	}
	
	/**
	 * 批量新增记录
	 * @param tList
	 * @throws SQLException
	 */
	public void insert(List<T> tList) throws SQLException{
		if(tList == null || tList.size() < 1)
			return ;
		for(int i=0;i<tList.size();i++)
			insert(tList.get(i));
	}
	
	/**
	 * 依据表中主键ID单条删除记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(int id) throws SQLException{
		int count = 0;
		if(isMemcacheOpen()){
			String key = new StringBuffer(16).append(this.className).append(DELIMITER).append(id).toString();
			MemcachedCacheUtils.getClient().delete(key);
			count = SqlBuilder.getSqlMapClient().delete(prefix + ".delete", id);
			MemcachedCacheUtils.getClient().delete(key);
		}else{
			count = SqlBuilder.getSqlMapClient().delete(prefix + ".delete", id);
		}
		return count;
	}
	
	/**
	 * 依据实体Bean List批量删除记录
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public int delete(List<T> list) throws SQLException{
		if(list == null || list.size() < 1)
			return 0;
		
		//循环list逐个删除并记录删除数量返回
		int total = 0;
		for(int i=0;i<list.size();i++){
			total += delete(list.get(i).getId());
		}
		return total;
	}
	
	/**
	 * 单条修改记录
	 * @param t
	 * @return
	 * @throws SQLException
	 */
	public int update(T t) throws SQLException{
		int count = 0;
		if(isMemcacheOpen()){
			String key = new StringBuffer(16).append(this.className).append(DELIMITER).append(t.getId()).toString();
			//System.out.println(key);
			MemcachedCacheUtils.getClient().delete(key);
			count = SqlBuilder.getSqlMapClient().update(prefix + ".update", t);
			MemcachedCacheUtils.getClient().delete(key);
		}else{
			count = SqlBuilder.getSqlMapClient().update(prefix + ".update", t);
		}
		return count;
	}
	
	/**
	 * 依据实体Bean List批量修改记录
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public int update(List<T> list) throws SQLException{
		if(list == null || list.size() < 1)
			return 0;
		
		//循环list逐个修改并记录修改数量返回
		int total = 0;
		for(int i=0;i<list.size();i++){
			total += update(list.get(i));
		}
		return total;
	} 
	
	 
	/**
	 * 默认构造函数
	 */
	protected BasicDAO(){
		init();
	}
	
	/**
	 * 初始化相关操作
	 */
	@SuppressWarnings("unchecked")
	private void init(){
		entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		className = entityClass.getName();
		prefix = className.substring(className.lastIndexOf(".") + 1);
		if(prefix.endsWith("DTO"))
			prefix = prefix.substring(0, prefix.length() - 3);
	}
	private boolean isMemcacheOpen(){
		return ConfigUtil.isMemcacheOpen();
	}
}
