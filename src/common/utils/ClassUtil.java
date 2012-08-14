package common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.dto.BasicDTO;


/**
 * Class工具类
 * @description 封装一般Class操作方法
 */
public class ClassUtil {
	
	/**
	 * 获取类中所有标准get方法 去除get方法中包含参数的方法 未找到返回null
	 * @param clazz
	 * @return
	 */
	public static Method[] getGetMethods(Class<?> clazz){
		List<Method> getMethodList = new ArrayList<Method>();
		
		//获取类中所有方法
		Method[] allMethods = clazz.getMethods();
		
		//循环类中所有方法 筛选 以get开头并且不包含参数 的方法
		for(int i=0;i<allMethods.length;i++){
			Method method = allMethods[i];
			String methodName = method.getName();
			if(methodName.startsWith("get") && method.getGenericParameterTypes().length == 0){
				getMethodList.add(method);
			}
		}
		
		//未找到方法返回null
		if(getMethodList.size() < 1)
			return null;
		else{
			Method[] methods = new Method[getMethodList.size()];
			return (Method[])getMethodList.toArray(methods);
		}
	}
	
	/**
	 * 获取类中所有标准Set方法 去除set方法中未包含参数的方法
	 * @param clazz
	 * @return
	 */
	public static Method[] getSetMethods(Class<?> clazz){
		List<Method> setMethodList = new ArrayList<Method>();
		
		//获取类中所有方法
		Method[] allMethods = clazz.getMethods();
		
		//循环类中所有方法 筛选 以set开头并且包含参数 的方法
		for(int i=0;i<allMethods.length;i++){
			Method method = allMethods[i];
			String methodName = method.getName();
			if(methodName.startsWith("set") && method.getGenericParameterTypes().length > 0){
				setMethodList.add(method);
			}
		}
		
		//未找到方法返回null
		if(setMethodList.size() < 1)
			return null;
		else{
			Method[] methods = new Method[setMethodList.size()];
			return (Method[])setMethodList.toArray(methods);
		}
	}
	
	/**
	 * 将一个Map<String, Object>中的value注入到与其key相同的DTO属性中。
	 * 测试中，暂不建议使用(lixinglei)
	 * @param whereMap
	 * @param dto
	 * @throws Exception
	 */
	public static void map2DTO(Map<String, Object> whereMap, BasicDTO dto)
			throws IllegalAccessException{
		Set<String> keySet = whereMap.keySet();
		Iterator<String> it = keySet.iterator();
		Field field = null;
		Class<?> Clazz = dto.getClass();
		while(it.hasNext()){
			String key = it.next();
			try{
				field = Clazz.getDeclaredField(key);
				field.setAccessible(true);
				field.set(dto, whereMap.get(key));
			}catch(NoSuchFieldException e){
				//ignore
			}
		}
	}
	
	/**
	 * 私有构造函数 防止实例化此类
	 */
	private ClassUtil(){}
}
