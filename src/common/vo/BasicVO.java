package common.vo;


import java.lang.reflect.Method;

import common.utils.ClassUtil;



public class BasicVO {
	
	
	/**
	 * 重写实toString方法 方便System.out时打印信息
	 * @author QiaoGuangqing
	 */
	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer(32);
		Method[] getMethods = ClassUtil.getGetMethods(this.getClass());		//获取实体Bean所有标准get方法
		try{
			for(int i=0;i<getMethods.length;i++){
				Method method = getMethods[i];
				if(method.getName().equals("getClass"))
					continue;
				stringBuffer.append("{").append(method.getName().substring(3)).append(":").append(method.invoke(this)).append("}");
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return stringBuffer.toString();
	}
}
