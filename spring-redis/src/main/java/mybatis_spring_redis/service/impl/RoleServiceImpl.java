package mybatis_spring_redis.service.impl;

import mybatis_spring_redis.bean.Role;
import mybatis_spring_redis.dao.RoleDao;
import mybatis_spring_redis.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    // 角色DAO，方便执行SQL
    @Autowired
    private RoleDao roleDao = null;

    /**
     * 使用@Cacheable定义缓存策略 当缓存中有值，则返回缓存数据，否则访问方法得到数据 通过value引用缓存管理器，通过key定义键
     *
     * @param id 角色编号
     * @return 角色
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @Cacheable(value = "redisCacheManager", key = "'redis_role_'+#id",unless="#result == null")
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    /**
     * 使用@CachePut则表示无论如何都会执行方法，最后将方法的返回值再保存到缓存中
     * 使用在插入数据的地方，则表示保存到数据库后，会同期插入到Redis缓存中
     *
     * @param role 角色对象
     * @return 角色对象（会回填主键）
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager", key = "'redis_role_'+#result.id")
    public Role insertRole(Role role) {
        roleDao.insertRole(role);
        return role;
    }

    /**
     * 使用@CachePut，表示更新数据库数据的同时，也会同步更新缓存
     *
     * @param role 角色对象
     * @return 影响条数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CachePut(value = "redisCacheManager", key = "'redis_role_'+#role.getId()")
    public Role updateRole(Role role) {
        roleDao.updateRole(role);
        //然后再调用数据库查询的结果返回,这样redis缓存中才会更新最新的消息，而不是更新别的值，比如1，
        // 【对于更新完数据库之后，在更新缓存的值，返回值很重要，比如拿return null;进行测试】；
        // 在当前这个例子中的方法insertRole没有出错，是因为返回值很正常
        // return null;
        return roleDao.getRole(role.getId());
    }

    /**
     * 使用@CacheEvict删除缓存对应的key
     *
     * @param id 角色编号
     * @return 返回删除记录数
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    @CacheEvict(value = "redisCacheManager", key = "'redis_role_'+#id")
    public int deleteRole(Long id) {
        return roleDao.deleteRole(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public List<Role> findRoles(String roleName, String note) {
        return roleDao.findRoles(roleName, note);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int insertRoles(List<Role> roleList) {
        for (Role role : roleList) {
            //同一类的方法调用自己方法，产生自调用[插入：失效]问题
            this.insertRole(role);
        }
        return roleList.size();
    }
}
