package com.corfid.fideicomisos.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.corfid.fideicomisos.utilities.StringUtil;

public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "domain";

    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String username = obtainUsername(request);
        String domain = obtainDomain(request);

        if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(domain)) {

            attemptAuthentication(request, response);
        }

        super.doFilter(req, res, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        final String dbValue = obtainDomain(request);
        request.getSession().setAttribute("domain", dbValue);
        return super.attemptAuthentication(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String domain = obtainDomain(request);

        String usernameDomain = username.trim() + "-" + domain.trim();
        return new UsernamePasswordAuthenticationToken(usernameDomain, password);
    }

    protected String obtainDomain(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
    }

}