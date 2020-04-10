package springdb;

import bean.Student;
import dao01.IEmployeeMapper;
import dao01.IStudentMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class testMapperScannerConfigurer {
    @Test
    public void  testMapperScannerConfigurer(){

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:sqlSessionFactoryBean-MapperScannerConfigurer-04.xml");
        IStudentMapper studentMapper = ctx.getBean(IStudentMapper.class);
        List<Student> lr = studentMapper.getStudentListByLastName("%lp%");
        System.out.println(lr);

        System.out.println("---------------->");
        IEmployeeMapper employeeMapper = ctx.getBean(IEmployeeMapper.class);
        Long a = employeeMapper.countByExample();
        System.out.println(a);

    }
}
