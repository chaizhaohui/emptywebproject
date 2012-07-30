package common.filter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;
/**
 * 初始化Log4j相对路径
 * @author QiaoGuangqing
 * @date 2012-04-18
 */
public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException{
		String prefix = config.getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j");
		System.setProperty("appHome", prefix);
		
		if(file != null){
			PropertyConfigurator.configure(prefix + file);
		}
	}
}