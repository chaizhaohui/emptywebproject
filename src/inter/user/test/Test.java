package inter.user.test;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import common.utils.FileUtil;
public class Test{
	
	public static void main(String...args){
		
		String urlStr = "http://localhost/emptyServer/interface/user/getUserList.do";
		String xmlStr = "<getUserListReq>" +
				"<cpage>1</cpage>" +
				"<pageSize>2</pageSize>" +
				"<userId>1</userId>" +
				"</getUserListReq>";
		new Test().execute(xmlStr, urlStr, "1");
	}
	
	public String execute(String xmlStr, String urlStr, String userId){
		System.err.println("====================start=====================");
		System.out.println("url:"+urlStr);
		System.out.println("reqXml:-----------------------------------");
		System.out.println(xmlStr);
		InputStream input = null;
		DataOutputStream printout = null;
		try {
			byte[] xmlData = xmlStr.getBytes("UTF-8"); 
			//获得到位置服务的链接   
			URL url = new URL(urlStr);
			URLConnection urlCon = url.openConnection();
			urlCon.setDoOutput(true);
			urlCon.setDoInput(true);
			urlCon.setUseCaches(false);

			//将xml数据发送到位置服务   
			urlCon.setRequestProperty("Content-Type", "application/xml");
			urlCon.setRequestProperty("X-ResultType", "xml");
			urlCon.setRequestProperty("Content-length", String.valueOf(xmlData.length));
			//urlCon.setRequestProperty("X-Client-Agent", clientAgent);
			//urlCon.setRequestProperty("X-Device-Id", deviceNum);
			urlCon.setRequestProperty("X-User-Id", userId);

			printout = new DataOutputStream(urlCon.getOutputStream());
			printout.write(xmlData);
			printout.flush();
			input = urlCon.getInputStream();
			Document document = new SAXReader().read(input);
			
			System.out.println("rspXml:-----------------------------------");
			System.out.println(document.asXML());
			System.err.println("=====================end======================");
			return document.asXML();
		} catch (Exception e) {
			//有异常则在测试报告中记录请求地址，参数和异常信息
			e.printStackTrace();
			return null;
		} finally {
			FileUtil.close(printout);
			FileUtil.close(input);
		}
	}
}
