package service;

import bean.Student;
import dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements  StudentService {

    //在mybatis-spring中，是通过spring容器中拿到mapper接口对象的，此处通过spring的自动注入装配的bean拿到mapper接口对象；
    @Autowired
    private StudentMapper studentMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED)
    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Transactional(propagation = Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED)
    @Override
    public void delStudent(String name) {
        studentMapper.delStudent(name);
    }
}
