package service;

import bean.Student;
import dao.StudentMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements  StudentService, ApplicationContextAware {

    //在mybatis-spring中，是通过spring容器中拿到mapper接口对象的，此处通过spring的自动注入装配的bean拿到mapper接口对象；
    @Autowired
    private StudentMapper studentMapper;

    private  ApplicationContext ctx;

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)//REQUIRES_NEW
    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);

        //throw  new RuntimeException("go--------insertStudent--》异常测试----->");
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED)
    @Override
    public void delStudent(String name) {
        studentMapper.delStudent(name);
        //throw  new RuntimeException("go--------delStudent--》异常测试----->");
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED)
    @Override
    public void insertListStudent(List<Student> students) {
        //从容器中获取一个对象，其实这个对象是一个代理对象，避免自调用传播属性失效的问题（不能产生指定的多个事务，产生一个事务：与日志中：...ThreadPoolAsynchronousRunner...的数量对应）；
        StudentService studentService = ctx.getBean(StudentService.class);
        students.forEach(a->{
            // 自调用失效问题；
            // 同一个类中当前方法调用一个事务标注的方法B，且方法B传播属性为Propagation.REQUIRES_NEW；
            // 但是方法B没有在新的事务中执行，这就是自调用失效的情况，因为这成了方法之间的调用了，没有通过获取Bean（方法所在的Bean）的形式调用，
            // 因为获取Bean对象返回的是一个代理的对象，解决方案，通过spring容器获取bean的代理对象；
            //insertStudent(a);
            studentService.insertStudent(a);
        });




    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx=applicationContext;
    }
}
