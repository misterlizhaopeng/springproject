package dao;

import java.util.List;
import java.util.Map;

import bean.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface IStudentMapper {
	public List<Student> getStudentListByLastName(String lastName);
	public Student getStudentByIdAndName(@Param("id") int id, @Param("name") String name);
}