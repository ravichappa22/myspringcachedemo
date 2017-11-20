package com.example.cache;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
