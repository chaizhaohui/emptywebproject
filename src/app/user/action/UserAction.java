package app.user.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.user.service.UserService;
import app.user.vo.UserVO;

import common.action.AppBasicAction;
import common.dto.UserDTO;
import common.utils.Page;

public class UserAction extends AppBasicAction{
	
	private Page page;
	private int cpage;
	private List<UserVO> dataList ;
	
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
		dataList = new ArrayList<UserVO>();
		UserVO userVO = null;
		for(UserDTO userDTO : userDTOList){
			userVO = new UserVO();
			cp(userDTO,userVO);
			dataList.add(userVO);
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
		userVO.setUserId(userDTO.getId());
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
	public List<UserVO> getDataList() {
		return dataList;
	}
	public void setDataList(List<UserVO> dataList) {
		this.dataList = dataList;
	}
}
