package cn.com.importsel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class MT {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        String[] arrs = ctx.getBeanDefinitionNames();
        List<String> list = Arrays.asList(arrs);
        list.forEach(a->{
            System.out.println(a);
        });


    }
}
