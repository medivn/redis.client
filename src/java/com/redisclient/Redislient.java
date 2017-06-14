package com.redisclient;

import org.apache.commons.collections.IteratorUtils;
import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Collections;
import java.util.function.Consumer;


/**
 * Created by medivn on 17-6-9.
 */
public class Redislient {
    public static RedissonClient getRedisson(){
        Config config = new Config();
        String address = "127.0.0.1:6379";
        config.useSingleServer().setAddress(address).setConnectionPoolSize(20).setPassword("WshkeMrVWh4Y4KRcbtvanDUh2");
        RedissonClient redisson=Redisson.create(config);
        return redisson;
    }

    public static void main(String[] args) {
        RedissonClient redissonClient = getRedisson();
        RList list = redissonClient.getList("testList");
        list.add("123456");
        RKeys rKeys = redissonClient.getKeys();
        System.out.println(rKeys.count());
        rKeys.getKeys().forEach(key -> {
            System.out.println(key);
            RList list1 =redissonClient.getList(key);
            list1.forEach(value -> System.out.println(value));
        });
        list.clear();
        redissonClient.shutdown();
    }
}
