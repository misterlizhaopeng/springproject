package mybatis_spring_redis.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import mybatis_spring_redis.bean.Role;

import java.util.List;

@Repository
public interface RoleDao {

    public Role getRole(Long id);

    public int deleteRole(Long id);

    public int insertRole(Role role);

    public int updateRole(Role role);

    public List<Role> findRoles(@Param("roleName") String roleName, @Param("note") String note);

}
