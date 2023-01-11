package zw.co.bancabc.filestorageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import zw.co.codehive.strixpay.strixpaycommons.config.FeignClientSecurityConfig;
import zw.co.codehive.strixpay.strixpaycommons.config.LocalRibbonClientConfiguration;
import zw.co.codehive.strixpay.strixpaycommons.security.JwtAuthenticationEntryPoint;

@SpringBootApplication(scanBasePackages = "zw.co.codehive.strixpay")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"zw.co.codehive.strixpay"})
@Import({FeignClientSecurityConfig.class})
@RibbonClient(name = "FILE-STORAGE-SERVICE", configuration = LocalRibbonClientConfiguration.class)
public class FileStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileStorageServiceApplication.class, args);
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

}