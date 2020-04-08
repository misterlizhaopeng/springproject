package aopxml.test;

import aopxml.Ins.VerifierAnimal;
import aopxml.Ins.VerifierAnimalImpl;
import aopxml.Ins.MyParamt;
import aopxml.Ins.Animal;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

public class MT {
    @Test
    public void testAopXml() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:BeanAop.xml");
        String[] ss = ctx.getBeanDefinitionNames();
        List<String> list = Arrays.asList(ss);
        list.forEach(a -> {
            //System.out.println(a);
        });
        System.out.println("------------------------>");


        Animal an = ctx.getBean(Animal.class);

        //引入测试 start

        VerifierAnimal v = (VerifierAnimal) an;
        if (v.ver().equals("1")){
            System.out.println("-----------》正确引入");
//        an.output("1");
            MyParamt pa = new MyParamt();
            pa.setId(12);
            pa.setName("id 为12");
            an.passPamaTest(pa, "老A");
        }
        //引入测试 end


    }

}
