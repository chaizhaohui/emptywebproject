package common.utils;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户Cookies操作类
 * @date 2012-03-14
 */
public class Cook {
	/** Cookie中保存UserName的Key */
	public static final String USER_NAME = "cuname";
	/** Cookie中保存UserPwd的Key */
	public static final String USER_PWD = "cupwd";
	
	/**
	 * 取得Cookies
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		String theCook = "";
		Cookie temp = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				temp = cookies[i];
				if (temp.getName().equals(name))
					theCook = temp.getValue();
			}
		}
		return General.sqlStr(theCook);
	}

	/**
	 * 设置Cookies
	 * @param response
	 * @param name
	 * @param value
	 * @param age 单位(s)
	 * @param path
	 * @param domain
	 */
	public static void setCookie(HttpServletResponse response, String name,
			String value, int age, String path, String domain) {
		Cookie theCook = new Cookie(name, value);
		if(age > 0)
			theCook.setMaxAge(age);
		if(General.isNotEmpty(path))
			theCook.setPath(path);
		if(General.isNotEmpty(domain))
			theCook.setDomain(domain);
		response.addCookie(theCook);
	}
	
	/**
	 * 删除指定Cookies
	 * @param response
	 * @param name
	 * @param path
	 */
	public static void removeCookie(HttpServletResponse response,String name,String path){
		Cookie theCook = new Cookie(name,null);
		theCook.setMaxAge(0);
		if(General.isNotEmpty(path))
			theCook.setPath(path);
		response.addCookie(theCook);
	}
	
	private Cook(){}
}
