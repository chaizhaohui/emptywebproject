package inter.user.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.user.service.UserService;
import app.user.vo.UserVO;

import common.action.AppBasicAction;
import common.action.InterBasicAction;
import common.dto.UserDTO;
import common.utils.Page;
import common.utils.ParamType;
import common.utils.ParamType.PARAM_TYPE;

public class getUserList extends InterBasicAction{
	
	private Page page;
	
	@ParamType(PARAM_TYPE.IN)
	private int cpage;
	@ParamType(PARAM_TYPE.IN)
	private int pageSize;
	@ParamType(PARAM_TYPE.OUT)
	private List<UserVO> dataList ;
	@Override
	public String doAction() throws Exception {
		UserService userService = UserService.getInstance();
		Map<String,Object> whereMap = new HashMap<String,Object>();
		int amount = userService.getCount(whereMap);
		page = new Page(cpage,amount,pageSize);
		whereMap.put("start", page.getStart());
		whereMap.put("end", page.getEnd());
		List<UserDTO> userDTOList = UserService.getUserList(whereMap);
		if(userDTOList==null){
			return this.AUTO;
		}
		dataList = new ArrayList<UserVO>();
		UserVO userVO = null;
		for(UserDTO userDTO : userDTOList){
			userVO = new UserVO();
			cp(userDTO,userVO);
			dataList.add(userVO);
		}
		return this.AUTO;
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
