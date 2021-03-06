package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
/**
 * 字符编码转换过滤器
 * @author lixinglei
 *
 */
public class CharactorEncodingFilter extends HttpServlet implements Filter{
	private static final long serialVersionUID = 1L;
	
	private String encoding = null; 
	
	public void init(FilterConfig config){
		this.encoding = config.getInitParameter("encoding");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException{
		
		if(null != this.encoding && this.encoding.length() > 0){
			request.setCharacterEncoding(this.encoding);
			response.setCharacterEncoding(this.encoding);
		}
		chain.doFilter(request, response);
	}
}
