package com.mmk.project.RedisManager;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisManager {
	@Value("${redis.key.expiration-minutes}")
	private long redisKeyExpiryMinutes;

	@Autowired
	private StringRedisTemplate redisTemplate;


	public Boolean hasKey(String to, String from, String fxn) {
	  return redisTemplate.hasKey(fxn + to + from );
	}
	
	public String getKey(String to, String from, String fxn) {
		return redisTemplate.opsForValue.get(fxn+to+from);
	}

	public void setKey(String to, String from, String text, String fxn) {
			if(fxn == "RATE_LIMIT")			
				redisTemplate.opsForValue().set( fxn + to+from , text, 24*60, //redisKeyExpiryMinutes
	        TimeUnit.MINUTES);
			
			if(fxn == "STOP")			
				redisTemplate.opsForValue().set( fxn + to+from , text, 4*60, //redisKeyExpiryMinutes
	        TimeUnit.MINUTES);
	}


}
