package ru.itis.grant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.grant.security.entry.PermissionEntryPoint;
import ru.itis.grant.security.entry.TokenAuthenticationEntryPoint;
import ru.itis.grant.security.exception.PermissionException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
    @Autowired
    private PermissionEntryPoint permissionEntryPoint;

    public TokenAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, AuthenticationException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("Auth-Token");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("time: " + simpleDateFormat.format(new Date()) + " token: " + token);
        if (!isSecuredMethod(request)) {
            filterChain.doFilter(request, response);
        } else {
            permissionEntryPoint.commence(request, response, new PermissionException("Permissions"));
        }
    }

    private boolean isSecuredMethod(HttpServletRequest request) {
        return !(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/registration")
        || request.getRequestURI().equals("/v2/api-docs"));
    }

    private boolean isExpertMethod(HttpServletRequest request) {
        return (request.getRequestURI().contains("/experts"));
    }

    private boolean isCreatorMethod(HttpServletRequest request) {
        return (request.getRequestURI().contains("/creators"));
    }

    private void authenticateAndFilterChain(UserDetails user, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
