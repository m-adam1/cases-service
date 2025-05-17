package net.techmentor.cases_service.shared.infrastructure;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "net.techmentor.cases_service.accounts.shared")
public class FeignConfig {
} 
