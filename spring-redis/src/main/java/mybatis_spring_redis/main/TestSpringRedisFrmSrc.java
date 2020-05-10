package mybatis_spring_redis.main;

import mybatis_spring_redis.bean.Role;
import mybatis_spring_redis.config.MapperConfig;
import mybatis_spring_redis.dao.RoleDao;
import mybatis_spring_redis.service.RoleService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class TestSpringRedisFrmSrc {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MapperConfig.class);
//        DataSource bean = ctx.getBean(DataSource.class);
//        System.out.println(bean);
//        RoleDao bean1 = ctx.getBean(RoleDao.class);
//        List<Role> roles = bean1.findRoles("", "");//role_name_1   note_1
//        roles.forEach(a -> {
//            System.out.println(a.getId() + "," + a.getRoleName() + "," + a.getNote());
//        });

//        PlatformTransactionManager t = ctx.getBean(PlatformTransactionManager.class);
//        System.out.println(t);



        //获取角色服务类
        RoleService roleService = ctx.getBean(RoleService.class);
        Role role = new Role();
        role.setRoleName("role_name_1");
        role.setNote("role_note_1");
        //插入角色
        roleService.insertRole(role);

        //获取角色
        //查询的问题：
        //Exception in thread "main" java.lang.IllegalArgumentException:
        // Cache 'redisCacheManager' is configured to not allow null values but null was provided
        // 解决方法：unless="#result == null" 表示当结果为空的时候，不存入缓存
        Object r =  roleService.getRole(7L);
        System.out.println(r);



        roleService.deleteRole(13L);

        // 更新角色
        Role rr = new Role();
        rr.setRoleName("6");
        rr.setNote("6");
        rr.setId(15l);
        //更新存缓存有问题
        roleService.updateRole(rr);

        Object r15L =  roleService.getRole(15L);
        System.out.println(r15L);





    }

    @Test
    public void testRedisTemplate(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MapperConfig.class);
        RedisTemplate redis = ctx.getBean(RedisTemplate.class);
        Object o = redis.opsForValue().get("redis_role_" + 15);
        System.out.println("--------------------------------------------->"+o);
    }
}
