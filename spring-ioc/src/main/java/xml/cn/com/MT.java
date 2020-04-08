package xml.cn.com;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MT {

    @SuppressWarnings("resource")
    @Test
    public void testClassPathXmlApp() {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:bean.xml");
        String[] ns = ctx.getBeanDefinitionNames();
        List<String> list = Arrays.asList(ns);

        list.forEach(a -> {
            System.out.println(a);
        });

        System.out.println("----------------------------->");

        for (String ss : ns) {
            System.out.println(ss);
        }
//		System.out.println("无参生成的bean--->");
//		House house = ctx.getBean("house",House.class);
//		System.out.println(house.toString());
//		System.out.println("构造器注入值生成的bean--->");
//		House house1 = ctx.getBean("house1",House.class);
//		System.out.println(house1.toString());
//		System.out.println("setter注入值生成的bean--->");
//		BigHouse bigHouse = ctx.getBean("bigHouse",BigHouse.class);
//		System.out.println(bigHouse.toString());


        //ctx.destroy();

    }
}