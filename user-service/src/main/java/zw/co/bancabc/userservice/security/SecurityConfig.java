package zw.co.bancabc.userservice.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zw.co.bancabc.commonutils.handler.ExceptionHandlerFilter;
import zw.co.bancabc.commonutils.security.AuthServiceFeignClient;
import zw.co.bancabc.commonutils.security.JWTAuthenticationFilter;
import zw.co.bancabc.commonutils.security.JwtAuthenticationEntryPoint;
import zw.co.bancabc.commonutils.security.JwtTokenProvider;
import zw.co.bancabc.userservice.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtTokenProvider tokenProvider;
    private final AuthServiceFeignClient authServiceFeignClient;


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        //each Authentication provider is tested in order
        //if one passes then its following Authentication providers are skipped

        //DataBase Authentication with  mobile number
        auth.userDetailsService((UserDetailsService) userService).passwordEncoder(passwordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/secure/login/**", "/api/v1/users/signUp/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/users/reset-credentials").permitAll()
                .antMatchers("/", "/eureka/**").permitAll()
                .antMatchers("/api/v1/admin/users/").access("hasRole('ADMIN')")
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
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
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public ExceptionHandlerFilter exceptionHandlerFilter() {
        return new ExceptionHandlerFilter();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }
}