package common.utils;

/**
 * 分页标签工具类
 * @author QiaoGuangqing
 * @date 2012-03-29
 */
public class Page {
	private int start;
	private int end;
	private int totalPage;
	private int cpage;
	private int totalRow;
	private int pageSize;
    private String link;
	
    public Page(int cpage, int totalRow, int pageSize) {
		//如果pageSize为0 默认为20条
		if(pageSize == 0)
			pageSize = 20;
		
		this.totalRow = totalRow;
		this.pageSize = pageSize;
		this.cpage = (cpage <= 0) ? 1 : cpage;
		this.totalPage = totalRow / pageSize;
		if (totalRow % pageSize > 0)
			this.totalPage = this.totalPage + 1;
		if(this.cpage > this.totalPage) this.cpage = this.totalPage;
		
		/*
		 * oracle与mysql数据库切换更改,1为oracle，0为mysql.
		 * modify by ChaiZhaohui
		 * date 2012-05-22
		 */
		String database_type = ConfigUtil.getProperty(ConfigUtil.DATABASE_TYPE);
		if("0".equals(database_type)){
			if(totalRow==0){
				start=0;
				end = 0;
				totalPage = 0;
				return ;
			}
			//LIMIT #START#-1,#END#-#START#+1  
			this.start = ((this.cpage - 1) * pageSize);
			this.end = pageSize;
		}else{
			this.start = (this.cpage - 1) * pageSize;
			this.end = this.start + pageSize;
		}
//		this.start = (this.cpage - 1) * pageSize;
//		this.end = this.start + pageSize;
	}

	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getCpage() {
		return cpage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public String getLink() {
		if(General.isEmpty(this.link))
			return "cpage";
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
