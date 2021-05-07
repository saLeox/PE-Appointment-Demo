package com.gof.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;

@Configuration
public class MybatisPlusConfig {

	/**
	 *  添加乐观锁插件
	 * @ref https://mybatis.plus/guide/interceptor-optimistic-locker.html#optimisticlockerinnerinterceptor
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
	    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
	    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
	    return interceptor;
	}
}
