package service;

import bean.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentListServiceImpl implements StudentListService {
    Logger log = Logger.getLogger(StudentListServiceImpl.class);

    @Autowired
    private StudentService studentService;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public void insertStudent(List<Student> students) {

        // studentService.delStudent("5555");
        students.forEach(a -> {
            studentService.insertStudent(a);
//            try {
//
//
//            } catch (Exception ex) {
//                log.debug("catch--------->:"+ex);
//            }
        });

       //studentService.delStudent("5555");
    }
}
