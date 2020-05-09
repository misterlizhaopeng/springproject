package mybatis_spring_redis.main;

import mybatis_spring_redis.bean.Role;
import mybatis_spring_redis.config.MapperConfig;
import mybatis_spring_redis.dao.RoleDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestSpringRedisFrmSrc {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MapperConfig.class);
//        DataSource bean = ctx.getBean(DataSource.class);
//        System.out.println(bean);
        RoleDao bean1 = ctx.getBean(RoleDao.class);
        List<Role> roles = bean1.findRoles("", "");//role_name_1   note_1
        roles.forEach(a -> {
            System.out.println(a.getId() + "," + a.getRoleName() + "," + a.getNote());
        });

//        PlatformTransactionManager t = ctx.getBean(PlatformTransactionManager.class);
//        System.out.println(t);


    }
}
