package Conditionals;


import Component.CustomComponentClient;

public class CustomComponentClientConditionalOnBean implements CustomComponentClient {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Client conditional on bean");
    }
}
