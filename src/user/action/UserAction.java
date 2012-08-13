package user.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.service.UserService;
import user.vo.UserVO;
import common.action.BasicAction;
import common.dto.UserDTO;
import common.utils.Page;

public class UserAction extends BasicAction{
	
	private Page page;
	private int cpage;
	private List<UserVO> userVOList ;
	
	/**
	 * 完成数据合法性验证，如email格式，什么的
	 * @return
	 * @throws SQLException 
	 */
	public String showUserList() throws SQLException{
		UserService userService = UserService.getInstance();
		Map<String,Object> whereMap = new HashMap<String,Object>();
		int amount = userService.getCount(whereMap);
		page = new Page(cpage,amount,PAGESIZE);
		whereMap.put("start", page.getStart());
		whereMap.put("end", page.getEnd());
		List<UserDTO> userDTOList = UserService.getUserList(whereMap);
		if(userDTOList==null){
			this.setReturnUrl("/tpl/user/showUserList.html");
			return this.MARKER;
		}
		userVOList = new ArrayList<UserVO>();
		UserVO userVO = null;
		for(UserDTO userDTO : userDTOList){
			userVO = new UserVO();
			cp(userDTO,userVO);
			userVOList.add(userVO);
		}
		
		this.setReturnUrl("/tpl/user/showUserList.html");
		return this.MARKER;
	}
	/**
	 * 拷贝dto的属性到vo中去
	 * @param userDTO
	 * @param userVO
	 */
	private void cp(UserDTO userDTO, UserVO userVO) {
		// TODO Auto-generated method stub
		userVO.setPassword(userDTO.getPassword());
		userVO.setUserName(userDTO.getUserName());
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public List<UserVO> getUserVOList() {
		return userVOList;
	}

	public void setUserVOList(List<UserVO> userVOList) {
		this.userVOList = userVOList;
	}
}
