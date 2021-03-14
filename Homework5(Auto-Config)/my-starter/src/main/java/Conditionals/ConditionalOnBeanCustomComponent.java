package Conditionals;

import Component.CustomComponent;

public class ConditionalOnBeanCustomComponent implements CustomComponent {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Conditional on bean custom component");
    }
}
