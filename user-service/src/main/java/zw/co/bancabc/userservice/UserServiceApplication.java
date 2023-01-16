package zw.co.bancabc.userservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import zw.co.bancabc.commonutils.config.FeignClientSecurityConfig;
import zw.co.bancabc.commonutils.config.LocalRibbonClientConfiguration;
import zw.co.bancabc.commonutils.security.JwtAuthenticationEntryPoint;

@SpringBootApplication(scanBasePackages = "zw.co.bancabc")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"zw.co.bancabc"})
@Import({FeignClientSecurityConfig.class})
@RibbonClient(name = "USER-SERVICE", configuration = LocalRibbonClientConfiguration.class)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }
}