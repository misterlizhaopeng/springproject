package springdb;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testSqlSessionTemplate {
    @Test
    public void testSqlSessionTemp(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:sqlSessionFactoryBean-SQLSessionTemplate-02.xml");
        SqlSessionTemplate st = ctx.getBean(SqlSessionTemplate.class);
        Object o = st.selectList("dao.IStudentMapper.getStudentListByLastName","l%");
        System.out.println(o);

        Object b = st.selectList("dao.IStudentMapper.getStudentListByLastName","595b%");
        System.out.println(b);
    }
}
