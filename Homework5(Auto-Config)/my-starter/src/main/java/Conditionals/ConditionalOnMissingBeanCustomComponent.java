package Conditionals;

import Component.CustomComponent;

public class ConditionalOnMissingBeanCustomComponent implements CustomComponent {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Conditional on missing bean custom component");
    }
}
