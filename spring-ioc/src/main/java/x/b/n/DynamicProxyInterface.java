package x.b.n;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxyInterface 代理方法类：用来创建代理对象和执行代理动作(invoke)
 * 静态代理：只有代理一个类，不能实现一次编码动态性的代理多个类；
 * 动态代理：只要存在接口的类都可以通过代理方法实现代理
 * 动态代理分两步，1.bind (有2中写法，如下代码所示) 2.实现代理的逻辑
 * 
 * @author  misterLip
 * @date   2020年3月31日
 */
public class DynamicProxyInterface implements InvocationHandler {

	private Object target;
 
	//1.bind  写法1
	public Object bind(Object target) {
		this.target=target;//this.target表示实际执行的对象，要在下面的invoke中执行方法的时候使用到，作用：通过反射调用被代理对象的方法【】
		Object proxyIns= Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				 target.getClass().getInterfaces(), 
				this);
		return proxyIns;
	}
	
	
	/* 写法2
	public DynamicProxyInterface(Object target) {
		this.target=target;
	}
	
	//1.bind   
	public static Object bind(Object target) {
		Object proxyIns= Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				 target.getClass().getInterfaces(), 
				new DynamicProxyInterface(target));
		return proxyIns;
	}*/

	
	
	//2.实现代理的逻辑：设置代理对象和真实对象的【代理操作】
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("调用真实对象之前的操作");

		Object invoke = method.invoke(this.target, args);//此处就是通过反射实现的
		
		System.out.println("调用真实对象之前的操作");
		return invoke;
		
	}
}
