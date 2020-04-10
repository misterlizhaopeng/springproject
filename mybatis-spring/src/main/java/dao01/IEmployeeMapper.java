package dao01;

import inter.ExtBaseInter;
import org.springframework.stereotype.Repository;

//@Repository
public interface IEmployeeMapper extends ExtBaseInter {
    public Long countByExample();
}
