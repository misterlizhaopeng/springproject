package dao;

import bean.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentMapper {
	public List<Student> getStudentListByLastName(String lastName);
	public Student getStudentByIdAndName(@Param("id") int id, @Param("name") String name);
	public void insertStudent(Student student);
	public void delStudent(String name);
}