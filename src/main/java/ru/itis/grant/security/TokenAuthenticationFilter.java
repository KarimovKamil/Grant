package ru.itis.grant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.grant.security.entry.PermissionEntryPoint;
import ru.itis.grant.security.exception.IncorrectDataException;
import ru.itis.grant.security.exception.PermissionException;
import ru.itis.grant.validation.verification.Verification;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Order(2)
public class TokenAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private PermissionEntryPoint permissionEntryPoint;
    @Autowired
    private Verification verification;

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
        boolean isSecured = isSecuredMethod(request);
        System.out.println("Защищенный? " + isSecured);
        String uri = request.getRequestURI();
        System.out.println(uri);
        if (!isSecured) {
            filterChain.doFilter(request, response);
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                filterChain.doFilter(request, response);
                return;
            } else {
                if (null == token || "".equals(token)) {
                    permissionEntryPoint.commence(request, response, new PermissionException("Not enough permissions"));
                } else {
                    try {
                        verification.verifyTokenExistence(token);
                    } catch (IncorrectDataException e) {
                        permissionEntryPoint.commence(request, response, new PermissionException("Not enough permissions"));
                        return;
                    }
                    UserDetails userDetails = userDetailsService.loadUserByUsername(token);
                    authenticateAndFilterChain(userDetails, request, response, filterChain);
                    return;
                }
            }
            permissionEntryPoint.commence(request, response, new PermissionException("Not enough permissions"));
        }
    }


    private boolean isSecuredMethod(HttpServletRequest request) {
        return !(request.getRequestURI().contains("/login") || request.getRequestURI().contains("/registration")
                || isSwagger(request));
    }

    private void authenticateAndFilterChain(UserDetails user, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private boolean isSwagger(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return (uri.equals("/v2/api-docs") || uri.contains("swagger"));
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headersMap = new HashMap<>();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                System.out.println("Header name: " + name + "; Value: " + value);
                headersMap.put(name, value);
            }
        }
        return headersMap;
    }
}
