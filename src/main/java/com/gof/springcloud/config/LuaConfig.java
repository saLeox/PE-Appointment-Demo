package com.gof.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import com.gof.springcloud.constants.Constants;

@Configuration
public class LuaConfig {
	@Bean
	public DefaultRedisScript<Boolean> redisScript() {
		DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
		redisScript.setScriptSource(
				new ResourceScriptSource(new ClassPathResource(Constants.REDIS_LUA_SCRIPT_AVAILABILITY)));
		redisScript.setResultType(Boolean.class);
		return redisScript;
	}
}