package common.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import common.dto.UserDTO;
import common.sqlbuilder.SqlBuilder;

/**
 * 用户DAO
 * @author QiaoGuangqing
 * @date 2012-04-21
 */
public class UserDAO extends BasicDAO<UserDTO> {
	
	private static UserDAO usersDAO; 
	private UserDAO(){super();}
	public static UserDAO getInstance(){
		if(usersDAO == null){
			synchronized(UserDAO.class){
				if(usersDAO == null){
					usersDAO = new UserDAO();
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
	public List<UserDTO> getUserList(Map<String,Object> whereMap) throws SQLException{
		List<Integer> list = (List<Integer>)SqlBuilder.getSqlMapClient().queryForList("User.getUserList", whereMap);
		if(list != null && list.size() > 0)
			return this.getById(list);
		return null;
	}
	public int getCount(Map<String, Object> whereMap) throws SQLException {
		// TODO Auto-generated method stub
		return (Integer) SqlBuilder.getSqlMapClient().queryForObject("User.getCount", whereMap);
	}
}
