package springdb;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class TestJdbcTemplate {
    @Test
    public void testJdbcTemplte() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:datasourceBySpringCls-01.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from dept");
        list.forEach(a -> {
            System.out.println(a);
        });
    }
}
