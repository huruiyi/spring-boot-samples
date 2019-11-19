package com.example.NoSql;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JediClusterTest {

    public static void main(String[] args) throws IOException {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(2);

        HostAndPort hp0 = new HostAndPort("192.168.146.128", 6379);
        HostAndPort hp1 = new HostAndPort("192.168.146.128", 6380);
        HostAndPort hp2 = new HostAndPort("192.168.146.128", 6381);
        HostAndPort hp3 = new HostAndPort("192.168.146.128", 6382);
        HostAndPort hp4 = new HostAndPort("192.168.146.128", 6383);
        HostAndPort hp5 = new HostAndPort("192.168.146.128", 6384);


        Set<HostAndPort> hps = new HashSet<HostAndPort>();
        hps.add(hp0);
        hps.add(hp1);
        hps.add(hp2);
        hps.add(hp3);
        hps.add(hp4);
        hps.add(hp5);

        // 超时，最大的转发数，最大链接数，最小链接数都会影响到集群
        JedisCluster jedisCluster = new JedisCluster(hps, 5000, 10, config);

        jedisCluster.set("k1", "v1");
        System.out.println(jedisCluster.get("k1"));

        jedisCluster.close();

    }
}
