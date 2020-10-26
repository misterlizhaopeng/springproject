package x.b.n;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class Cat implements InitializingBean, ApplicationContextAware {

    @Autowired
    private BigDog bigDog;

    @PostConstruct
    private BigCat bigCat() {
        System.out.println("PostConstruct");
        return new BigCat();
    }


    Integer a;
    Integer b;

    public Cat() {
        System.out.println("bigDog:" + bigDog);
    }

    public Cat(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer cal(Integer a) {
        System.out.println("calc:" + a);
        return a;
    }

    @Override
    public String toString() {
        return "Cat [a=" + a + ", b=" + b + "]";
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }
}
