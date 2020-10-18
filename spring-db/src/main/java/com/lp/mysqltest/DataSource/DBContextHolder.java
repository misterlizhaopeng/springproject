package com.lp.mysqltest.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(DBContextHolder.class);

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }
    public static void master() {
        set(DBTypeEnum.MASTER);
        logger.info("当前数据源：{} 切换主数据源-写入", get());
    }

    public static void slave() {
        //  轮询
        int index = counter.getAndIncrement() % 3;
        if (counter.get() > 9999) {
            counter.set(-1);
        }
        if (index == 0) {
            set(DBTypeEnum.SLAVE1);
            logger.info("当前数据源：{} 切换从数据源(SLAVE1)-读出", get());
        }else if (index == 1){
            set(DBTypeEnum.SLAVE2);
            logger.info("当前数据源：{} 切换从数据源(SLAVE2)-读出", get());
        }else {
            set(DBTypeEnum.SLAVE3);
            logger.info("当前数据源：{} 切换从数据源(SLAVE3)-读出", get());
        }
    }
}
