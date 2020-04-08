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

        Animal a = ctx.getBean(Animal.class);
        //引入start
        VerifierAnimal verifierAnimal = (VerifierAnimal) a;
        String ver = verifierAnimal.ver();
        if (ver.equals("1")) {
            //            a.output("1");
            MyParamt pa = new MyParamt();
            pa.setId(100);
            pa.setName("1000");
           // a.passPamaTest(pa,"22");
        }
        //引入end


        System.out.println("------------------------------------");

        //us.testMethodOne02("90");
//        String[] s = ctx.getBeanDefinitionNames();
//        for (String sa:s             ) {
//
//            System.out.println(sa);
//        }
    }
}