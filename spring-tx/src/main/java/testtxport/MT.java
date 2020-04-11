package testtxport;

import bean.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StudentService;

public class MT {
    @Test
    public void testTx(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationctxMapperScannerConfigurer.xml");
        StudentService s = ctx.getBean(StudentService.class);

        for (int i = 0; i < 5; i++) {
            Student student=new Student();
            student.setName("5555");
            student.setAge(8);
            student.setDeptId(44);
            s.insertStudent(student);
        }

        s.delStudent("go");
    }
}
