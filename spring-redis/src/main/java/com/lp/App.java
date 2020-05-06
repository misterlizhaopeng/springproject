package com.lp;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    private static JedisSentinelPool jedisSentinelPool;
    public static void main(String[] args) {
        //哨兵信息
        HashSet<String> sentinels =
                new HashSet<>(Arrays.asList("192.168.25.140:26379", "192.168.25.140:26380", "192.168.25.140:26381"));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMinIdle(5);

        // 创建连接池
        //JedisSentinelPool pool = new JedisSentinelPool(clusterName, sentinels);
        JedisSentinelPool pool = new JedisSentinelPool("host6379", sentinels,jedisPoolConfig,"lp");
        Jedis jedis = null;
        try {
            // 获取客户端
            jedis = pool.getResource();
            jedis.auth("lp");
            // 执行命令
            jedis.set("key", "bbb");
            while (true) {

                try {
                    System.out.println(jedis.getClient().getHost() + ":" + jedis.getClient().getPort() + "@" + jedis.get("key"));
                } catch (Exception e) {
                    System.out.println("getConntion  error,waiting  5s,will try again..." + e.getMessage());
                    //Thread.sleep(5000);
                    try {
                        jedis = pool.getResource();
                    } catch (Exception e1) {
                        System.out.println("getResource  error2,waiting  more,will try again..." + e.getMessage());
                    }
                }
                Thread.sleep(200);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        pool.close();

    }
}


