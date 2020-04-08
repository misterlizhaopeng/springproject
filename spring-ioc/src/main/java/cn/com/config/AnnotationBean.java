package cn.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import cn.com.bean.Dog;
import cn.com.bean.MyFilterType;
import cn.com.bean.Person;
import cn.com.bean.Red;
import cn.com.bean.Shit;
import cn.com.bean.UserService;
import cn.com.condi.LinuxCondi;
import cn.com.condi.WindowsCondi;

//@Configuration
//@ComponentScans(value = {
//		@ComponentScan(
//				value = "cnx.com", 
//				includeFilters = { @Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Service.class }) }, 
//				useDefaultFilters = false)
//		})




@Configuration
@ComponentScans(value = { @ComponentScan(
		value = "cn.com", 
		includeFilters = {
				@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { UserService.class })//按照指定类型，就是后面classes就是设置的类型
				,@Filter(type = FilterType.CUSTOM, classes = { MyFilterType.class })
				}, 
		useDefaultFilters = false) })

//@Import(value= {Person.class,MyImportBeanDefinitionRegistrar.class})  //,SelBean.class
//@Configuration
public class AnnotationBean {
	
	
//	@Scope("prototype")  // singleton
//	@Bean(initMethod="a",destroyMethod="b")
//	public Red  red() {
//		return new Red();
//	}
	
	
//	@Bean("my-factoryBean")
//	public MyFactoryBean myFactoryBean() {
//		return  new MyFactoryBean();
//	}

//	@Scope("singleton")// prototype
//	@Lazy
//	@Conditional(value= {WindowsCondi.class})
	
//	@Bean("person-01")
//	public Person person() {
//		System.out.println("--------->");
//		return new Person();
//	}
}
