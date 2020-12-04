package com.yanyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OAuth2GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2GatewayApplication.class, args);
    }

}
