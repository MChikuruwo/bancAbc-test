package zw.co.bancabc.filestorageservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zw.co.codehive.strixpay.strixpaycommons.handlers.ExceptionHandlerFilter;
import zw.co.codehive.strixpay.strixpaycommons.security.AuthServiceFeignClient;
import zw.co.codehive.strixpay.strixpaycommons.security.JWTAuthenticationFilter;
import zw.co.codehive.strixpay.strixpaycommons.security.JwtAuthenticationEntryPoint;
import zw.co.codehive.strixpay.strixpaycommons.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthServiceFeignClient authServiceFeignClient;

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final JwtTokenProvider tokenProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/eureka/**").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                // Filter to handle authentication exceptions and non-controller exceptions
                .addFilterBefore(exceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new JWTAuthenticationFilter(tokenProvider, authServiceFeignClient), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/assets/**");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/");
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }


    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }
}
