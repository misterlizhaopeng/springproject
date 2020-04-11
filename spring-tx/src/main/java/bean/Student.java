package bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("stus")
public class Student implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private int age;
    private int deptId;



    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
