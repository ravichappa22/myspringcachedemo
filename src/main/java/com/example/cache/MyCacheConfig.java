package com.example.cache;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@EnableCaching
@Configuration
public class MyCacheConfig extends CachingConfigurerSupport{
	  
	@Bean
	  public KeyGenerator keyGenerator() {
	    return new KeyGenerator() {
	      @Override
	      public Object generate(Object o, Method method, Object... objects) {
	        // This will generate a unique key of the class name, the method name,
	        // and all method parameters appended.
	        StringBuilder sb = new StringBuilder();
	        sb.append(o.getClass().getName());
	        sb.append(method.getName());
	        for (Object obj : objects) {
	          sb.append(obj.toString());
	        }
	        return sb.toString();
	      }
	    };
	  }
	
	
	 @Bean
	  public JedisConnectionFactory redisConnectionFactory() {
	    JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

	    // Defaults
	    redisConnectionFactory.setHostName("127.0.0.1");
	    redisConnectionFactory.setPort(6379);
	    return redisConnectionFactory;
	  }

	  @Bean
	  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
	    RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
	    redisTemplate.setConnectionFactory(cf);
	    return redisTemplate;
	  }

	  @Bean
	  @Primary
	  public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(120l);
	    return cacheManager;
	  }
	  
	  @Bean
	  public RedisCacheManager cacheManagerLastName(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(180l);
	    return cacheManager;
	  }
}
