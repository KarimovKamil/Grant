package ru.itis.grant.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itis.grant.security.entry.PermissionEntryPoint;
import ru.itis.grant.security.entry.TokenAuthenticationEntryPoint;
import ru.itis.grant.security.user.UserDetailsServiceImpl;

import javax.servlet.Filter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint() {
        return new TokenAuthenticationEntryPoint();
    }

    @Bean
    public PermissionEntryPoint permissionEntryPoint() {
        return new PermissionEntryPoint();
    }

    @Bean
    public Filter tokenAuthenticationFilter(UserDetailsService userDetailsService) {
        return new TokenAuthenticationFilter(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
