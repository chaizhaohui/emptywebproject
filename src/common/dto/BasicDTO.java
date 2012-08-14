package common.dto;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import common.utils.ClassUtil;
import common.utils.General;

/**
 * 所有对应数据库实体化Bean基类 DTO包中所有实体Bean都需要继承此基类
 * @date 2012-04-18
 * @description 定义表主键id 所有子类不需要重复定义
 *              重写toString方法 使得System.out有意义 方便调试
 */
public class BasicDTO implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	
	private int id;			//任意表主键 继承此类后不需要在子类中再次定义ID 统一用此ID
	
	/**
	 * 判断请求的用户的终端是否为pad
	 * @return
	 */
	protected boolean isPad(){
		return General.isPad(ServletActionContext.getRequest());
	}
	
	/**
	 * 重写实体Bean的toString方法 方便System.out时打印实体Bean信息
	 * @author QiaoGuangqing
	 */
	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer(32);
		Method[] getMethods = ClassUtil.getGetMethods(this.getClass());		//获取实体Bean所有标准get方法
		try{
			for(int i=0;i<getMethods.length;i++){
				Method method = getMethods[i];
				method.setAccessible(true);
				stringBuffer.append("{").append(method.getName().substring(3)).append(":").append(method.invoke(this)).append("}");
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 标准克隆方法 规范实体bean创建 并无任何意义
	 */
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
