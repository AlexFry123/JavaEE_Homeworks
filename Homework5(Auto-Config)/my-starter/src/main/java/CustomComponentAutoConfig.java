import Conditionals.ConditionalOnBeanCustomComponent;
import Conditionals.ConditionalOnMissingBeanCustomComponent;
import Conditionals.ConditionalOnPropertyCustomComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomComponentAutoConfig {
    @Bean
    @ConditionalOnProperty(value = "my.property", havingValue = "property")
    ConditionalOnPropertyCustomComponent onPropertyCustomComponent(){
        return new ConditionalOnPropertyCustomComponent();
    }

    @Bean
    @ConditionalOnBean(ConditionalOnPropertyCustomComponent.class)
    ConditionalOnBeanCustomComponent onBeanCustomComponent(){
        return new ConditionalOnBeanCustomComponent();
    }

    @Bean
    @ConditionalOnMissingBean
    ConditionalOnMissingBeanCustomComponent onMissingBeanCustomComponent(){
        return new ConditionalOnMissingBeanCustomComponent();
    }

}
