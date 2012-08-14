package common.tag;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 后台分页标签
 * @date 2012-03-29
 */
public class PageTag extends ComponentTagSupport{
	private static final long serialVersionUID = 1L;
	
	private String cpage;			//当前页
	private String totalpage;		//总页数
	private String pagesize;		//显示页
	private String exceptioname;	//去除参数名称
	private String pageparamname;	//分页参数
	private String tcount;			//总条数
	private HttpServletRequest request;
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,HttpServletResponse arg2){
		this.request = arg1;
		return new Pages(arg0);
	}

	@Override
	protected void populateParams(){
		super.populateParams();
		Pages pages = (Pages)component;
		pages.setRequest(this.request);
		pages.setCpage(this.cpage);
		pages.setExceptioname(this.exceptioname);
		pages.setPagesize(this.pagesize);
		pages.setTotalpage(this.totalpage);
		pages.setTcount(this.tcount);
		pages.setPageparamname(this.pageparamname);
	}

	public void setCpage(String cpage){
		this.cpage = cpage;
	}

	public void setPageparamname(String pageparamname){
		this.pageparamname = pageparamname;
	}

	public void setTotalpage(String totalpage){
		this.totalpage = totalpage;
	}

	public void setPagesize(String pagesize){
		this.pagesize = pagesize;
	}

	public void setExceptioname(String exceptioname){
		this.exceptioname = exceptioname;
	}

	public void setTcount(String tcount){
		this.tcount = tcount;
	}
}
