package springdb;

import bean.Student;
import dao.IEmployeeMapper;
import dao.IStudentMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class testMapperFactoryBean {
    @Test
    public void  testMapperFactoryBean(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:sqlSessionFactoryBean-MapperFactoryBean-03.xml");
        IStudentMapper studentMapper = ctx.getBean(IStudentMapper.class);
        List<Student> lr = studentMapper.getStudentListByLastName("%lp%");
        System.out.println(lr);

        System.out.println("---------------->");
        IEmployeeMapper employeeMapper = ctx.getBean(IEmployeeMapper.class);
        Long a = employeeMapper.countByExample();
        System.out.println(a);

    }
}
