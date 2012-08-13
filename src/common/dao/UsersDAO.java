package common.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import common.dto.UsersDTO;
import common.sqlbuilder.SqlBuilder;

/**
 * 用户DAO
 * @author QiaoGuangqing
 * @date 2012-04-21
 */
public class UsersDAO extends BasicDAO<UsersDTO> {
	
	private static UsersDAO usersDAO; 
	private UsersDAO(){super();}
	public static UsersDAO getInstance(){
		if(usersDAO == null){
			synchronized(UsersDAO.class){
				if(usersDAO == null){
					usersDAO = new UsersDAO();
				}
			}
		}
		return usersDAO;
	}
	
	/**
	 * 依据条件获取记录总数量
	 * @param whereMap[status,userName,haveNickName,nickName,haveHeadImg,lastActDateStart,lastActDateEnd,isRecommend]
	 * @return
	 * @throws SQLException
	 */
	/*public int getCount(Map<String,Object> whereMap) throws SQLException{
		return (Integer)SqlBuilder.getSqlMapClient().queryForObject("Users.getCount", whereMap);
	}*/
	
	/**
	 * 依据条件分页获取记录
	 * @param whereMap[start,end,status,userName,haveNickName,nickName,haveHeadImg,lastActDateStart,lastActDateEnd,isRecommend]
	 * @return
	 * @throws SQLException
	 */
	/*@SuppressWarnings("unchecked")
	public List<UsersDTO> getByPage(Map<String,Object> whereMap) throws SQLException{
		List<Integer> list = (List<Integer>)SqlBuilder.getSqlMapClient().queryForList("Users.getByPage", whereMap);
		if(list != null && list.size() > 0)
			return this.getById(list);
		return null;
	}*/

}
