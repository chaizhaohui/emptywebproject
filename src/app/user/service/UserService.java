package app.user.service;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.dao.UserDAO;
import common.dto.UserDTO;
import common.service.BasicService;
import common.sqlbuilder.SqlBuilder;

public class UserService extends BasicService{
	
private static UserService userService = null;
	
	/**
	 * 获取后台用户Service实例
	 * @return
	 */
	public static UserService getInstance(){
		if(userService == null){
			synchronized(UserService.class){
				if(userService == null)
					userService = new UserService();
			}
		}
		return userService;
	}

	public int getCount(Map<String, Object> whereMap) throws SQLException {
		// TODO Auto-generated method stub
		UserDAO userDAO = UserDAO.getInstance();
		return userDAO.getCount(whereMap);
	}
	/**
	 * 业务逻辑层，封装业务,此处并不需要开启事物，但考虑实例的完整性，开启事物
	 * @param whereMap
	 * @return
	 * @throws SQLException 
	 */
	public static List<UserDTO> getUserList(Map<String, Object> whereMap) throws SQLException {
		// TODO Auto-generated method stub
		UserDAO userDAO = UserDAO.getInstance();
		SqlMapClient sqlMapClient = SqlBuilder.getSqlMapClient();
		List<UserDTO> userDTOList = null;
		try{
			sqlMapClient.startTransaction();
			userDTOList = userDAO.getUserList(whereMap);
			sqlMapClient.commitTransaction();
		}finally{
			sqlMapClient.endTransaction();
		}
		return userDTOList;
	}
}
