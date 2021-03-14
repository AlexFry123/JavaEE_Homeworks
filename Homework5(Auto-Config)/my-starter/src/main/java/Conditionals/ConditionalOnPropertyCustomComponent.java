package Conditionals;

import Component.CustomComponent;

public class ConditionalOnPropertyCustomComponent implements CustomComponent {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Conditional on property custom component");
    }
}