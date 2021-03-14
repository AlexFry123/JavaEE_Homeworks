package Conditionals;

import Component.CustomComponentClient;

public class CustomComponentClientConditionalOnMissingBean implements CustomComponentClient {
    @Override
    public void afterPropertiesSet() {
        System.out.println("Client conditional on missing bean");
    }
}
