package common.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页Action
 * @date 2012-04-19
 */
public class IndexAction extends AppBasicAction {
	
	
	public String toIndex(){
		return ActionSupport.SUCCESS;
	}
}
