package mybatis_spring_redis.service;

public interface RedisTemplateService {

    /*流水线*/
    public void exePipeline();
    /*事务*/
    public void exeTransaction();
    /*执行多个命令*/
    public void exeMultiCommand();

}
