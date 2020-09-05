package aop.test;


import aop.Ins.*;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MT {
    @Test
    public void testOneAspect() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AOPConfig.class);


        UserService us = ctx.getBean(UserService.class);
        us.testMethodOne("1");

        System.out.println("--------------------------------------------------->");

        Animal animal = ctx.getBean(Animal.class);
        //引入start
        VerifierAnimal vera = (VerifierAnimal) animal;
        String ver = vera.ver();
        if (ver.equals("1")) {
            //            animal.output("1");
            MyParamt pa = new MyParamt();
            pa.setId(100);
            pa.setName("1000");
            animal.passPamaTest(pa,"22");
        }
        //引入end


        System.out.println("--------------------------------------------------->");

        //us.testMethodOne02("90");
//        String[] s = ctx.getBeanDefinitionNames();
//        for (String sa:s             ) {
//
//            System.out.println(sa);
//        }
    }
}