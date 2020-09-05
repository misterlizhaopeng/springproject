package aop.Ins;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public void testMethodOne(String id) {
//       int a= 1/0;
        System.out.println("proxy-method-测试连接点，传入的id："+id);
    }

    @Override
    public void testMethodOne02(String id) {
        System.out.println("proxy-method-测试连接点-02，传入的id："+id);
    }
}


//@Component
//public class UserServiceImpl   {
//
//    public void testMethodOne(String id) {
////       int a= 1/0;
//        String str= "测试连接点，传入的id："+id;
//        System.out.println(str);
//    }
//
//    public void testMethodOne02(String id) {
//        String str= "测试连接点-02，传入的id："+id;
//        System.out.println(str);
//    }
//}