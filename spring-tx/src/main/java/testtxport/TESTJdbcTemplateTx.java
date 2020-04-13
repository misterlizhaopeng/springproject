package testtxport;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

//编程式事务-jdbctemplate test
public class TESTJdbcTemplateTx {
    @Test
    public void testJdbcTemplateTx(){

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:JdbcTemplateAppCtx.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

        //事务定义器
        TransactionDefinition transactionDefinition=new DefaultTransactionDefinition();
        //事物管理器
        PlatformTransactionManager platformTransactionManager = ctx.getBean(PlatformTransactionManager.class);
        TransactionStatus status = platformTransactionManager.getTransaction(transactionDefinition);
        //1.上面两行代码为：开启事务管理

        try {

            // int i=8/0;
            int update = jdbcTemplate.update("DELETE FROM student WHERE NAME ='5555';");
            // 此时数据库事务已经交给【事务管理器】进行管理了，所以到了此行，删除行为不会立马提交；
            int i=8/0;
            //2.提交事务
            platformTransactionManager.commit(status);
        }catch (Exception ex){
            //2.回滚事务
            platformTransactionManager.rollback(status);
            System.out.println("算法错误");
        }


    }
}
