package service;


import bean.Student;

import java.util.List;

public interface StudentService {
    public void insertStudent(Student student);
    public void delStudent(String name);
    public void insertListStudent(List<Student> students);
}
