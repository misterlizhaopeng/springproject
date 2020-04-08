package lp.annotation.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import cc.nn.gg.XmlBean01;

@Component
public class Autowired_Test {
	// 1.什么是自动装配：spring自己发现bean，自己进行装配;
	// @Autowired//2.注解@Autowired解释：按照类型进行装配，如果指定的类型没有，程序会报错，但可以这样设置：@Autowired(required=false)，程序就不会报错了；
	private House house;

	// 3.有时候，@Autowired不仅可以用在属性上，也可以通过setter方法实现自动装配；
	// 4.大部分情况下，自动装配可以完成“约定大于配置的”规则，是的配置减少，但是当自动加载的类型存在多个，可以通过@Qualifier进行指定；
	// 5.注解：@Primary，一般标注在组件上，表示该类型的实例优先被【自动装配】的类型选择；
	@Autowired
	public void setHouse(House house) {
		this.house = house;
	}

	@Autowired
	@Qualifier(value = "xmlBean01")
	private XmlBean01 bean01;

	public void output() {
		System.out.println(house.toString());
		System.out.println("--->xmlBean01：" + bean01.toString());

	}
}