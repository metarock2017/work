package org.redrock.ljj;

import redis.clients.jedis.Jedis;

public class RedisUtill {
    private  static Jedis jedis = null;
    public static Jedis getRedisInstance() {
        if (jedis == null)
            return new Jedis("127.0.0.1", 6379);
        return jedis;
    }
}
