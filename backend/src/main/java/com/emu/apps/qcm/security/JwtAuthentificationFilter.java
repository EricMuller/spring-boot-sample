package com.emu.apps.qcm.security;

import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

class JwtAuthentificationFilter extends GenericFilterBean {

    private final JwtTokenAuthenticationService tokenAuthenticationService;

    protected JwtAuthentificationFilter(JwtTokenAuthenticationService taService) {
        this.tokenAuthenticationService = taService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest) req);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(req, res); // always continue
    }
}

