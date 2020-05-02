package spring.expr.lp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class MT2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] names = ctx.getBeanDefinitionNames();
        List<String> asList = Arrays.asList(names);
        asList.forEach(a->{
            System.out.println(a);
        });

    }
}
