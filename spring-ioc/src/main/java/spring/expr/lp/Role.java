package spring.expr.lp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * 
 * 
 * 
 * 我们都知道，对于注解@Value里面的符号区分：$是获取属性文件里面的变量值，而#是用在spring表达式运算
 * 1.Bean的属性和方法
 * 
 * 给角色类赋值，让其他的组件(ElBean)调用该类的值；
 * 
 * */
@Component
public class Role {
//	@Value("1")
	@Value("#{100}")
	private Long id;
//	@Value("${role_name_1}")
	@Value("#{'这是一个角色的名称'}")
	private String roleName;
	@Value("#{'这是角色的注释'}")
	private String note;
	//2.spring表达式调用静态常量和方法
	@Value("#{T(Math).PI}")
	private double pi;
	@Value("#{T(Math).random()}")
	private double random;

	public double getPi() {
		return pi;
	}

	public void setPi(double pi) {
		this.pi = pi;
	}


	public double getRandom() {
		return random;
	}


	public void setRandom(double random) {
		this.random = random;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
