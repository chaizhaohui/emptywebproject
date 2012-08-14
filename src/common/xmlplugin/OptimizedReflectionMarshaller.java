package common.xmlplugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OptimizedReflectionMarshaller {
	private static final Log log = LogFactory.getLog(OptimizedReflectionMarshaller.class);
	private static final String JAVA = "java.";
	private static final String JAVAX = "javax.";

	/**
	 * Info on a single field and the corresponding getter method
	 */
	private static class FieldMethodPair {
		private String fieldName;

		private Method getterMethod;

		public FieldMethodPair(String fieldName, Method getterMethod) {
			this.fieldName = fieldName;
			this.getterMethod = getterMethod;
		}

		public String getFieldName() {
			return fieldName;
		}

		public Method getGetterMethod() {
			return getterMethod;
		}
	}

	/**
	 * Returns the marshalled XML representation of the parameter object
	 */
	public static String getXML(Object obj, String paramName) {
		StringBuffer sb = new StringBuffer();
		String className = paramName;
			
		//首字母为大写则改小写 去末尾VO 其余不变
		className = className.substring(0,1).toLowerCase() + className.substring(1);
		if(className.endsWith("VO")){
			className = className.substring(0, className.length() - 2);
		}
			
		sb.append("<" + className + ">");
		marshal(obj, sb);
		sb.append("</" + className + ">\n");
			
		return sb.toString();
	}

	/**
	 * Returns getter function for the specified field
	 */
	private static Method getGetter(Class<?> clazz, String fieldName) {
		try {
			// for example, for 'name' we will look for 'getName'
			String getMethodName = fieldName.substring(0, 1);
			getMethodName = getMethodName.toUpperCase();
			getMethodName = "get" + getMethodName
					+ fieldName.substring(1, fieldName.length());
			Method getMethod = clazz.getMethod(getMethodName);
			return getMethod;
		} catch (NoSuchMethodException nsme) {
			return null;
		}
	}

	/**
	 * Returns a list of all fields that have getters
	 */
	private static List<?> getAllGetters(Class<?> clazz) {
		HashMap<String,Object> gettersMap = new HashMap<String,Object>();
		try {
			// check if have in cache
			if (gettersMap.containsKey(clazz.getName()))
				return (List<?>) gettersMap.get(clazz.getName());

			List<FieldMethodPair> fieldList = new LinkedList<FieldMethodPair>();
			Class<?> currClazz = clazz;
			while (true) {
				Field[] fields = currClazz.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field currField = fields[i];
					int modifiers = currField.getModifiers();
					// check if not static and has getter
					if (!Modifier.isStatic(modifiers)) {
						Method getterMethod = getGetter(clazz, currField
								.getName());
						if (getterMethod != null) {
							FieldMethodPair fmp = new FieldMethodPair(currField
									.getName(), getterMethod);
							fieldList.add(fmp);
						}
					}
				}
				currClazz = currClazz.getSuperclass();
				if (currClazz == null)
					break;
			}

			// store in cache
			gettersMap.put(clazz.getName(), fieldList);

			return fieldList;
		} catch (Exception ex) {
			log.error("OptimizedReflectionMarshaller - [getAllGetters] throw Exception. Cause by : " + ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * Checks whether the specified class implements Collection interface
	 */
	private static boolean isImplementsCollection(Class<?> clazz) {
		HashMap<String,Boolean> collectionsMap = new HashMap<String,Boolean>();
		
		String className = clazz.getName();
		// check in cache
		if (collectionsMap.containsKey(className)) {
			return ((Boolean) collectionsMap.get(className)).booleanValue();
		}

		boolean result = Collection.class.isAssignableFrom(clazz);

		// store in cache
		collectionsMap.put(className, new Boolean(result));
		return result;
	}

	private static void appendFormatted(Object obj, StringBuffer sb) {
		String content = obj.toString();
		
		if(obj instanceof String){
			if(!content.equals("")){
				sb.append("<![CDATA[").append(content).append("]]>");
			}
		}else
			sb.append(content);
		
//		String strRepresentation = obj.toString();
//		int len = strRepresentation.length();
//		for (int i = 0; i < len; i++) {
//			char c = strRepresentation.charAt(i);
//			switch (c) {
//			case '&':
//				sb.append("&amp;");
//				break;
//			case '<':
//				sb.append("&lt;");
//				break;
//			case '>':
//				sb.append("&gt;");
//				break;
//			case '\'':
//				sb.append("&apos;");
//				break;
//			case '\"':
//				sb.append("&quot;");
//				break;
//			default:
//				sb.append(c);
//			}
//		}
	}

	private static void marshal(Object obj, StringBuffer sb) {
		try {
			Class<?> clazz = obj.getClass();
			String className = clazz.getName();

			// check if implements Collection
			if (isImplementsCollection(clazz)) {
				Collection<?> cobj = (Collection<?>) obj;
				Iterator<?> it = cobj.iterator();
				
				String name = "";
				while (it.hasNext()) {
					Object eobj = it.next();
					if(isBasicClass(eobj.getClass())){
						name = "value";
					}else{
						name = eobj.getClass().getSimpleName();
						//首字母为大写则改小写 结尾去VO 其余不变
						name = name.substring(0,1).toLowerCase() + name.substring(1);
						//if(className.endsWith("VO")){
						if(name.endsWith("VO")){
							name = name.substring(0, name.length() - 2);
						}
					}

					sb.append("<" + name + ">");
					marshal(eobj, sb);
					sb.append("</" + name + ">\n");
				}
				return;
			}

			// check for primitive types
			if (className.startsWith(JAVA) || className.startsWith(JAVAX)) {
				appendFormatted(obj, sb);
				return;
			}

			// otherwise put all fields with getters
			List<?> allGetters = getAllGetters(clazz);
			Iterator<?> itGetters = allGetters.iterator();
			while (itGetters.hasNext()) {
				FieldMethodPair currGetter = (FieldMethodPair) itGetters.next();
				String currFieldName = currGetter.fieldName;
				Method currentGetter = currGetter.getterMethod;

				// call method
				currentGetter.setAccessible(true);
				Object val = currentGetter.invoke(obj);
				if (val != null) {
					sb.append("<" + currFieldName + ">");
					// call recursively
					marshal(val, sb);
					sb.append("</" + currFieldName + ">\n");
				}else{
					//注掉以下一句实现空节点隐藏
//					sb.append("<" + currFieldName + "/>\n");
				}
			}
		} catch (Exception ex) {
			log.error("OptimizedReflectionMarshaller - [marshal] throw Exception. Cause by : " + ex.getMessage(), ex);
		}
	}
	
	/**
	 * 判断类型是否为java自带的基础类型
	 * @param clazz
	 * @return
	 */
	public static boolean isBasicClass(Class<?> clazz){
		return clazz != null && clazz.getClassLoader() == null;
	}
	
	public static void main(String args[]){
		Integer i = new Integer(10);
		System.out.println(OptimizedReflectionMarshaller.getXML(i,"i"));
	}
}
