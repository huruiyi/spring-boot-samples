package org.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class Test_Redis {

  public int arraylength = 20000;
  public String ip = "127.0.0.1";
  public int port = 6379;
  public String[] keys = new String[arraylength / 2];

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

  //String[]{"key:0","v0","key:1","v1","key:2","v2","key:3","v3","key:4","v4"......."key:4999","v:4999"})
  public void initRedisData() {
    Jedis jedis = new Jedis(ip, port);
    String[] str = new String[arraylength]; //20000

    int j = 0;
    for (int i = 0; i < str.length / 2; i++) {
      str[j] = "key:" + i;
      str[j + 1] = "v" + i;
      j = j + 2;

      keys[i] = "key:" + i;
    }
    jedis.mset(str);
    jedis.close();
  }

  public void Test01() {
    initRedisData();//初始化数据  10000

    //NO PIPE删除
    long t = System.currentTimeMillis();
    delNopipe(keys); //del
    System.out.println(System.currentTimeMillis() - t);

    initRedisData();//初始化数据  10000
    //PIPE删除
    long t2 = System.currentTimeMillis();
    delBypipe(keys);
    System.out.println(System.currentTimeMillis() - t2);
  }


}
