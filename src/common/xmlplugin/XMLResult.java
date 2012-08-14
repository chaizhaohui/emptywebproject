package common.xmlplugin;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import common.utils.ParamType;
import common.utils.ParamType.PARAM_TYPE;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

public class XMLResult implements Result{
	private static final long serialVersionUID = 1L;
	
	public void execute(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		
		//获取Action中所有成员变量 加入注解过滤 返回XML类型文档
		Object actionObject = actionInvocation.getAction();
		Field []fields = actionObject.getClass().getDeclaredFields();
		Field []fatherFields = actionObject.getClass().getSuperclass().getDeclaredFields();
		
		Field field = null;
		int bodyCount = 0;
		
		StringBuffer xmlString = new StringBuffer(256);
		StringBuffer xmlBody = new StringBuffer(256);
		
		for(int i=0;i<fatherFields.length;i++){
			field = fatherFields[i];
			if(field.isAnnotationPresent(ParamType.class)){
				if(field.getAnnotation(ParamType.class).value().equals(PARAM_TYPE.OUT) || field.getAnnotation(ParamType.class).value().equals(PARAM_TYPE.IN_OUT)){
					//构造xml并放入Response
					Object obj = actionInvocation.getStack().findValue(field.getName());
					if(obj == null){
						if(!OptimizedReflectionMarshaller.isBasicClass(field.getType()))
							obj = field.getType().newInstance();
					}
					if(obj != null)
						xmlBody.append(OptimizedReflectionMarshaller.getXML(obj, field.getName()));
					bodyCount ++;
				}
			}
		}
		
		for(int i=0;i<fields.length;i++){
			field = fields[i];
			if(field.isAnnotationPresent(ParamType.class)){
				if(field.getAnnotation(ParamType.class).value().equals(PARAM_TYPE.OUT) || field.getAnnotation(ParamType.class).value().equals(PARAM_TYPE.IN_OUT)){
					//构造xml并放入Response
					Object obj = actionInvocation.getStack().findValue(field.getName());
					if(obj == null){
						if(!OptimizedReflectionMarshaller.isBasicClass(field.getType()))
							obj = field.getType().newInstance();
					}
					if(obj != null)
						xmlBody.append(OptimizedReflectionMarshaller.getXML(obj, field.getName()));
					bodyCount ++;
				}
			}
		}
		
		xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		//将Action名称 + Rsp封装为root
		String actionName = actionObject.getClass().getSimpleName() + "Rsp";
		
		xmlString.append("<").append(actionName).append(">\n");
		xmlString.append(xmlBody);
		xmlString.append("</").append(actionName).append(">");
		
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		response.setHeader("Content-Type", "application/xml");
		
		ByteArrayOutputStream baseOut = new ByteArrayOutputStream(512);
		OutputStream outputStream = baseOut;
		
		String acceptGzip = request.getHeader("Encoding-Type");
		if("gzip".equalsIgnoreCase(acceptGzip)){
			response.setHeader("Encoding-Type", "gzip");
			outputStream = new GZIPOutputStream(baseOut);
		}
		
		byte []byteXml = xmlString.toString().getBytes("UTF-8");
		outputStream.write(byteXml);
		outputStream.close();
		
		response.setHeader("Content-Length", String.valueOf(baseOut.size()));
		response.setHeader("Or-Content-Length", String.valueOf(byteXml.length));
		response.setCharacterEncoding("UTF-8");
		baseOut.writeTo(response.getOutputStream());
		baseOut.close();
	}
}
