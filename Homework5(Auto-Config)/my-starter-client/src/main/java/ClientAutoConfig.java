import Component.CustomComponentClient;
import Conditionals.ConditionalOnBeanCustomComponent;
import Conditionals.ConditionalOnMissingBeanCustomComponent;
import Conditionals.CustomComponentClientConditionalOnBean;
import Conditionals.CustomComponentClientConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientAutoConfig {
    @Bean
    @ConditionalOnBean(ConditionalOnBeanCustomComponent.class)
    CustomComponentClient conditionalOnBean(){
        return new CustomComponentClientConditionalOnBean();
    }

    @Bean
    @ConditionalOnMissingBean(ConditionalOnMissingBeanCustomComponent.class)
    CustomComponentClient conditionalOnMissingBean(){
        return new CustomComponentClientConditionalOnMissingBean();
    }
}
