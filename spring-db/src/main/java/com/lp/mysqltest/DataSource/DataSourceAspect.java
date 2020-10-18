package com.lp.mysqltest.DataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(0)
public class DataSourceAspect {
    protected static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("(@annotation(com.lp.mysqltest.DataSource.DataSource)) && " +
            "(execution(* com.lp.mysqltest.Dao.*.insert*(..)) || " +
            "execution(* com.lp.mysqltest.Dao.*.delete*(..)) || " +
            "execution(* com.lp.mysqltest.Dao.*.update*(..)) || " +
            "execution(* com.lp.mysqltest.Dao.*.select*(..)))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void read(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        DataSource source = method.getAnnotation(DataSource.class);//获取注解内容
        logger.info("当前数据源：{}", DBContextHolder.get());
        switch (source.type()) {
            case MASTER:
                DBContextHolder.master();
                break;
            case SLAVE:
                DBContextHolder.slave();
                break;
            case SLAVE1:
                DBContextHolder.slave();
                break;
            case SLAVE2:
                DBContextHolder.slave();
                break;
            case SLAVE3:
                DBContextHolder.slave();
                break;
            default:
                DBContextHolder.master();
        }
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        logger.info("清理数据源 当前源：{}", DBContextHolder.get());
        DBContextHolder.clear();
    }

}
