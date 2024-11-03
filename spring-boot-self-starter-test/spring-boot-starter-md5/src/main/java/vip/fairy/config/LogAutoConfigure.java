package vip.fairy.config;


import org.aopalliance.aop.Advice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.fairy.ano.LogProperties;
import vip.fairy.service.LogAspect;

@Configuration
@EnableConfigurationProperties({LogProperties.class})
//表示在application配置文件中必须配置log.switch.enabled = true才启动自动配置
@ConditionalOnProperty(prefix = "log.switch", value = "enabled", havingValue = "true")
public class LogAutoConfigure {
    @Bean
    //Advice.class是aop切面中关键的切面方法类(@Before，@After等)
    //程序中有Advice.class类说明需要使用切面功能，这时才加载自定义的切面类
    @ConditionalOnClass(Advice.class)
    public LogAspect webLogAspect(LogProperties logProperties){
        return new LogAspect(logProperties);
    }
}
