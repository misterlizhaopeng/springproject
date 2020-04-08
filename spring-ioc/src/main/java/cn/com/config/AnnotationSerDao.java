package cn.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import com.lp.dao.UserDao;
import com.lp.dao.impl.UserDaoImpl;

@ComponentScan(value= {"com.lp"})
public class AnnotationSerDao {
	
	//@Primary
	@Bean("userDao-BEAN")
	public UserDao userDao() {
		return new UserDaoImpl();
	}
	@Bean("userDao-BEAN01")
	public UserDao userDao01() {
		UserDaoImpl userdao = new UserDaoImpl();
		System.out.println(userdao);
		return userdao;
	}
}
