package com.maple.dal.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.maple.dal.config.interceptor.CommonUpdateInterceptor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Setter
@Import(CommonUpdateInterceptor.class)
public class MybatisConfig {

    @Autowired
    private CommonUpdateInterceptor commonUpdateInterceptor;

    @Bean
    public MybatisPlusInterceptor interceptorChain(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(commonUpdateInterceptor);
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }

}
