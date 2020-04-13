package testtxport;

import bean.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StudentListService;
import service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class MT {
    @Test
    public void testTx(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationctxMapperScannerConfigurer.xml");
        StudentService studentService = ctx.getBean(StudentService.class);
        StudentListService studentListService = ctx.getBean(StudentListService.class);

        List<Student> sts=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student student=new Student();
            student.setName("5555");
            student.setAge(8);
            student.setDeptId(44);
            //s.insertStudent(student);
            sts.add(student);
        }
//        studentListService.insertStudent(sts);

        // 测试自调用失效问题；
        studentService.insertListStudent(sts);
       // s.delStudent("go");
    }
}
