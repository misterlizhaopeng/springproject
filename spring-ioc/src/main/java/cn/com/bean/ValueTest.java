package cn.com.bean;

import org.springframework.beans.factory.annotation.Value;

public class ValueTest {
	@Value("${idx}")  /*${idx}：idx在文件中是变量，表示从文件中获取idx的值；当然，也可以像下面的name为字面量值*/
    private Integer id;
    @Value("张三xxxx")
    private String name;
    @Value("#{30-10}")
    private Integer age;
    @Value("${l.address.xdgo}")
    private String Address;
    @Value("#{T(Math).PI}")
    private String pi;
    @Value("#{T(java.lang.Math).PI}")
    private String p;
    @Value("#{T(Math).random()*10}")
    private String random;
    
    
    
	public String getRandom() {
		return random;
	}



	public void setRandom(String random) {
		this.random = random;
	}



	public String getP() {
		return p;
	}



	public void setP(String p) {
		this.p = p;
	}



	public String getPi() {
		return pi;
	}



	public void setPi(String pi) {
		this.pi = pi;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}



	@Override
	public String toString() {
		return "ValueTest [id=" + id + ", name=" + name + ", age=" + age + ", Address=" + Address + ", pi=" + pi
				+ ", p=" + p + ", random=" + random + "]";
	}



 


 


 
}
