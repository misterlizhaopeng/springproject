package x.b.n;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MT {


    @Test
    public void testDynamicProxy() {
        //具有接口的对象
        Dog dog = new Dog();
        Dog2 dog2 = new Dog2();

        DynamicProxyInterface dynamicProxy = new DynamicProxyInterface();
        Animal proxyDog = (Animal) dynamicProxy.bind(dog);//绑定真实对象
        proxyDog.eat();
        proxyDog.sound();
		
		/*Animal proxyDog = (Animal)DynamicProxyInterface.bind(dog);//绑定真实对象
		proxyDog.eat();
		proxyDog.sound();
		
		Animal proxyDog2 = (Animal)DynamicProxyInterface.bind(dog2);//绑定真实对象
		proxyDog2.eat();
		proxyDog2.sound();*/

    }


    /**
     * 反射：通过字符串获取类的实例，
     * 优点：接触程序的耦合性，增加代码的灵活性
     * 缺点：代码运行慢，但较实用，很多地方都用到了反射的技术，比如jdk的动态代理(中的invoke)
     * <p>
     * 类
     * 1.通过 Class.forName("类的全量名").newInstance();创建类实例
     * <p>
     * 获取方法
     * 1.Method method=实例.getClass().getMethod("方法名称",方法可变可变参数);method.invoke(实例对象,方法具体参数)
     *
     * @date 2020年3月31日
     * @author misterLip
     */
    @Test
    public void testReflect() throws Exception {

        //通过无参构造函数，反射创建类的实例
        Cat cat = (Cat) Class.forName("x.b.n.Cat").newInstance();
        System.out.println(cat);
        //通过有参参构造函数，反射创建类的实例
        System.out.println("---------");
        Cat cat02 = (Cat) Class.forName("x.b.n.Cat").getConstructor(Integer.class, Integer.class).newInstance(11, 22);
        System.out.println(cat02);

        System.out.println("---------");

        //通过[类实例]找到方法并执行方法
        Method method = cat02.getClass().getMethod("cal", Integer.class);
        Object invokeResult = method.invoke(cat02, 103);//invoke第一个参数为执行当前方法所在的类实例，第二个参数为方法的具体参数
        System.out.println("invokeResult:" + invokeResult);

        System.out.println("---------");
        //通过[类]找到方法并执行方法
        Method method2 = Cat.class.getMethod("cal", Integer.class);
        Object ir = method2.invoke(new Cat(), 104);//invoke第一个参数为执行当前方法所在的类实例，第二个参数为方法的具体参数
        System.out.println(ir);
    }


    @Test
    public void test() {
        @SuppressWarnings("resource")
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext(AppConfig.class);
        c.registerShutdownHook();
//        c.start();
//        c.stop();
        //Cat cat = c.getBean(Cat.class);

        //x.b.n

	/*	String[] s = c.getBeanDefinitionNames();
		List<String> list = Arrays.asList(s);
		list.forEach(a -> {
			System.out.println(a);
		});

		System.out.println("---------->");
		Cat c1 = c.getBean("cat",Cat.class);
		Cat c2 = c.getBean("cat02",Cat.class);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println("---------->");

		*/


    }

}