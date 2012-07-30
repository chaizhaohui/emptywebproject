package common.tag;


import java.io.Writer;
import java.util.Enumeration;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.components.Component;


import com.opensymphony.xwork2.util.ValueStack;
import common.utils.General;

/**
 * 后台分页标签实体
 * 
 * @author QiaoGuangqing
 * @date 2012-03-29
 */
public class Pages extends Component
{
    private HttpServletRequest request;
    
    private String cpage;
    
    private String totalpage;
    
    private String pagesize;
    
    private String exceptioname;
    
    private String pageparamname;
    
    private String tcount;
    
    public String getTcount()
    {
        return tcount;
    }
    
    public void setTcount(String tcount)
    {
        this.tcount = tcount;
    }
    
    @Override
    public boolean start(Writer writer)
    {
        boolean result = super.start(writer);
        StringBuffer sb = new StringBuffer();
        
        int Cpage = General.isNumber(cpage) ? Integer.parseInt(cpage) : 1;
        int Tpage = General.isNumber(totalpage) ? Integer.parseInt(totalpage) : 0;
        String pageparam = getPageParam(request, exceptioname);
        
        sb.append("<div class=\"ri_box_fot\"><div class=\"ri_fot_ri\"><span class=\"color_green\">共有<font class=\"green\">")
            .append(tcount)
            .append("</font>条记录,当前第 <font class=\"green\">")
            .append(Cpage)
            .append("/")
            .append(Tpage)
            .append("</font> 页</span></div><div class=\"ri_fot_le\">");
        if (Cpage <= 1)
        {
            sb.append("<span class=\"mrmp_top_page_btn\">最前页</span><span class=\"mrmp_page_up_btn\">上一页</span>");
        }
        else
        {
            sb.append("<span class=\"mrmp_top_page_btn\"><a href=\"?")
                .append(pageparamname)
                .append("=1")
                .append(pageparam)
                .append("\">最前页</a></span><span class=\"mrmp_page_up_btn\"><a href=\"?")
                .append(pageparamname)
                .append("=")
                .append(Cpage - 1)
                .append(pageparam)
                .append("\">上一页</a></span>");
        }
        if (Cpage >= Tpage)
            sb.append("<span class=\"mrmp_page_down_btn\">下一页</span><span class=\"mrmp_last_page_btn\">最尾页</span>");
        else
            sb.append("<span class=\"mrmp_page_down_btn\"><a href=\"?")
                .append(pageparamname)
                .append("=")
                .append(Cpage + 1)
                .append(pageparam)
                .append("\">下一页</a></span><span class=\"mrmp_last_page_btn\"><a href=\"?")
                .append(pageparamname)
                .append("=")
                .append(Tpage)
                .append(pageparam)
                .append("\">最尾页</a></span>");
        sb.append("<script>var gopage = function(){var topage=$('#cpage').attr('value');var url='?")
            .append(pageparamname)
            .append("='+topage+'")
            .append(pageparam)
            .append("';window.location.href=url;};</script>");
        sb.append("<span>&nbsp;&nbsp;&nbsp;跳转至<input id=\"cpage\" type=\"text\" class=\"ri_page_text\" />页</span><a href=\"javascript:gopage(")
            .append(Cpage)
            .append(");\"><span class=\"mrmp_go_btn\">GO</span></a></div>");
        
        try
        {
            writer.write(sb.toString());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    
    public String getPageParam(HttpServletRequest request, String exceptionParamNames)
    {
        StringBuffer params = new StringBuffer("");
        if (General.isEmpty(exceptionParamNames))
            exceptionParamNames = "cpage";
        Enumeration<?> e = request.getParameterNames();
        outer: while (e.hasMoreElements())
        {
            String key = (String)e.nextElement();
            StringTokenizer st = new StringTokenizer(exceptionParamNames, ",");
            while (st.hasMoreTokens())
            {
                String exceptionName = st.nextToken();
                if (key.equals(exceptionName))
                {
                    continue outer;
                }
            }
            String value = request.getParameter(key);
            value = General.encodeURL(value, "utf8");
            params.append("&" + key + "=" + value);
        }
        return params.toString();
    }
    
    public Pages(ValueStack stack)
    {
        super(stack);
    }
    
    public HttpServletRequest getRequest()
    {
        return request;
    }
    
    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    public String getCpage()
    {
        return cpage;
    }
    
    public void setCpage(String cpage)
    {
        this.cpage = cpage;
    }
    
    public String getTotalpage()
    {
        return totalpage;
    }
    
    public void setTotalpage(String totalpage)
    {
        this.totalpage = totalpage;
    }
    
    public String getPagesize()
    {
        return pagesize;
    }
    
    public void setPagesize(String pagesize)
    {
        this.pagesize = pagesize;
    }
    
    public String getExceptioname()
    {
        return exceptioname;
    }
    
    public void setExceptioname(String exceptioname)
    {
        this.exceptioname = exceptioname;
    }
    
    public void setPageparamname(String pageparamname)
    {
        this.pageparamname = pageparamname;
    }
    
}
