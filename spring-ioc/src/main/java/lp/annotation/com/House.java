package lp.annotation.com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/*
 * 注解@Component解释：spring ioc容器见到这个注解，会把这个类看做一个组件被扫描生成一个实例，
 * 而其中的value的值表示实例的id，类似xml配置bean中的id，也可以直接写成@Component("house01")，
 * 还可以不设置，默认为类名，首字母小写；
 * 
 * */
@Component//(value="house01")
public class House implements ApplicationContextAware{
	
	private ApplicationContext applicationContext ;
	
	@Value("10")
	private Long id;
	@Value("这是名字")
	private String name;
	@Value("这是地址：北京朝阳")//注解@Value解释： 直接给当前属性赋值，没有setter方法也可以在方法toString()中输出；
	private String address;
	
	@Value("${target}")
	private String target;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", name=" + name + ", address=" + address + ", target=" + target + "]";
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
}
