package com.example.Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/*
    psvm 生成main方法
 */
public class RedisDemo {
    public static boolean delBypipe(String[] keys) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        Pipeline pipe = jedis.pipelined();
        for (String key : keys) {
            //jedis.del(key);
            pipe.del(key); //del拼装
        }
        pipe.sync();//执行发送指令
        jedis.close();
        return true;
    }


    public static boolean delNopipe(String[] keys) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        for (String key : keys) {
            jedis.del(key);
        }
        jedis.close();
        return true;
    }

    public static void main(String[] args) {
        RedisTools.initRedisData();//初始化数据  10000

        //NO PIPE删除
        long t = System.currentTimeMillis();
        delNopipe(RedisTools.keys); //del
        System.out.println(System.currentTimeMillis() - t);


        RedisTools.initRedisData();//初始化数据  10000
        //PIPE删除
        long t2 = System.currentTimeMillis();
        delBypipe(RedisTools.keys);
        System.out.println(System.currentTimeMillis() - t2);

    }
}
