package dao01;

import bean.Student;
import inter.ExtBaseInter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public interface IStudentMapper extends ExtBaseInter {
	public List<Student> getStudentListByLastName(String lastName);
	public Student getStudentByIdAndName(@Param("id") int id, @Param("name") String name);
}