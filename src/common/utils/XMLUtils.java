package common.utils;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Element;

public class XMLUtils {
	private static final Logger logger = Logger.getLogger(XMLUtils.class);
	
	/**
	 * 指定节点名称进行 xmlToObject 节点名称忽略大小写
	 * @param el
	 * @param nodeName
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Object getContent(Element el, String nodeName, Type type){
		//基础类型转换 目前支持String Integer int double float 以后有了再加；lixinglei add Long and long at 20120416
		if(type.equals(String.class) || type.equals(Integer.class) || type.equals(int.class) 
				|| type.equals(double.class) || type.equals(float.class) || type.equals(Long.class) || type.equals(long.class) ){
			List list = el.elements();
			for(int i=0;i<list.size();i++){
				Element e = (Element)list.get(i);
				String name = e.getName();
				
				//节点值为空时跳过
				String value = e.getTextTrim();
				if(value == null || "".equals(value))
					continue;
				
				if(name.equalsIgnoreCase(nodeName)){
					if(type.equals(String.class))
						return value;
					else if(type.equals(Integer.class))
						return Integer.valueOf(value);
					else if(type.equals(int.class))
						return Integer.parseInt(value);
					else if(type.equals(double.class))
						return Double.parseDouble(value);
					else if(type.equals(float.class))
						return Float.parseFloat(value);
					//lixinglei add Long and long
					else if(type.equals(Long.class))
						return Long.valueOf(value);
					else if(type.equals(long.class))
						return Long.parseLong(value);
					else
						return null;
				}
			}
			//本层节点未找到值 遍历下一层节点继续找
			for(int i=0;i<list.size();i++){
				Element e = (Element)list.get(i);
				Object obj = getContent(e, nodeName, type);
				if(obj != null){
					if(type.equals(String.class))
						return (String)obj;
					if(type.equals(Integer.class))
						return Integer.valueOf((String)obj);
					if(type.equals(int.class))
						return Integer.parseInt((String)obj);
					if(type.equals(double.class))
						return Double.parseDouble((String)obj);
					if(type.equals(float.class))
						return Float.parseFloat((String)obj);
					if(type.equals(Long.class))
						return Long.valueOf((String)obj);
					if(type.equals(long.class))
						return Long.parseLong((String)obj);
					return null;
				}
			}
		} else {
			//泛型List转换 
			if(type instanceof ParameterizedType){
				ParameterizedType pType = (ParameterizedType)type;
				if(pType.getRawType().equals(List.class)){
					Type []types = pType.getActualTypeArguments();
					if(types.length != 1)
						return null;
					
					Type t = types[0];
					List resultList = new ArrayList();
					List list = el.elements();
					
					if(t.equals(String.class) || t.equals(Integer.class) || t.equals(int.class) || t.equals(double.class) || t.equals(float.class)){
						for(int i=0;i<list.size();i++){
							Element e = (Element)list.get(i);
							if(e.getName().equalsIgnoreCase(nodeName)){
								if(t.equals(String.class)){
									resultList.add(e.getTextTrim());
								}else if(t.equals(Integer.class)){
									resultList.add(Integer.valueOf(e.getTextTrim()));
								}else if(t.equals(int.class)){
									resultList.add(Integer.parseInt(e.getTextTrim()));
								}else if(t.equals(double.class)){
									resultList.add(Double.parseDouble(e.getTextTrim()));
								}else if(t.equals(float.class)){
									resultList.add(Float.parseFloat(e.getTextTrim()));
								}
							}
						}
					}else{
						for(int i=0;i<list.size();i++){
							Element e = (Element)list.get(i);
							if(e.getName().equalsIgnoreCase(nodeName)){
								
								List childList = e.elements();
								for(int k=0;k<childList.size();k++){
									Element childElement = (Element)childList.get(k);
									
									Object obj = null;
									try {
										obj = Class.forName(t.toString().replace("class ", "")).newInstance();
									} catch (Exception ex) {
										logger.error("XMLUtils - [getContent] New Instance " + t.toString().replace("class ", "") + " failed. Cause by:" + ex.getMessage(), ex);
										return null;
									}
									
									Field []fs = obj.getClass().getDeclaredFields();
									for(int j=0;j<fs.length;j++){
										Field f = fs[j];
										try{
											Method method = obj.getClass().getMethod("set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1),f.getType());
											Object value = getContent(childElement, f.getName().toLowerCase(), f.getGenericType());
											method.invoke(obj, value);
										} catch(NoSuchMethodException ex){
											logger.error("XMLUtils - [getContent] find set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1) + " Method failed. Cause by:" + ex.getMessage(), ex);
										} catch(Exception ex){
											logger.error("XMLUtils - [getContent] invoke method set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1) + " Method failed. Cause by:" + ex.getMessage(), ex);
										}
									}
									resultList.add(obj);
								}
							}
						}
					}
					
					if(resultList.size() > 0)
						return resultList;
					else{
						//本层节点未找到 继续找下一层节点
						for(int i=0;i<list.size();i++){
							Element e = (Element)list.get(i);
							Object obj = getContent(e, nodeName, type);
							if(obj != null){
								resultList.add(obj);
							}
						}
						if(resultList.size() > 0)
							return resultList;
						else
							return null;
					}
				}
			}else{
				//自定义类型转换
				List list = el.elements();
				if(list == null || list.size() < 1)
					return null;

				Element e = null;
				for(int i=0;i<list.size();i++){
					Element ex = (Element)list.get(i);
					if(ex.getName().equalsIgnoreCase(nodeName)){
						e = ex;
					}
				}
				if(e == null){
					//未找到节点 去下一层找
					for(int i=0;i<list.size();i++){
						Element ex = (Element)list.get(i);
						Object obj = getContent(ex, nodeName, type);
						if(obj != null)
							return obj;
					}
					return null;
				}
					
				
				Object resultObj = null;
				try{
					resultObj = Class.forName(type.toString().replace("class ", "")).newInstance();
				} catch(Exception ex){
					logger.error("XMLUtils - [getContent] New Instance " + type.toString().replace("class ", "") + " failed. Cause by:" + ex.getMessage(), ex);
					return null;
				}
				
				Field []fs = resultObj.getClass().getDeclaredFields();
				for(int i=0;i<fs.length;i++){
					Field f = fs[i];
					try {
						Method method = resultObj.getClass().getMethod("set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1), f.getType());
						Object value = getContent(e, f.getName().toLowerCase(), f.getGenericType());
						method.invoke(resultObj, value);
					} catch(NoSuchMethodException ex){
						logger.error("XMLUtils - [getContent] find set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1) + " Method failed. Cause by:" + ex.getMessage(), ex);
					} catch(Exception ex){
						logger.error("XMLUtils - [getContent] invoke method set" + f.getName().substring(0,1).toUpperCase() + f.getName().substring(1) + " Method failed. Cause by:" + ex.getMessage(), ex);
					}
				}
				return resultObj;
			}
		}
		return null;
	}
}
