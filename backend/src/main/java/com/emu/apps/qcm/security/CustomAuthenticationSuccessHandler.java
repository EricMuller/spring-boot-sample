package com.emu.apps.qcm.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenAuthenticationService jwtTokenAuthenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String principal = (String) authentication.getPrincipal();
        String jwt = jwtTokenAuthenticationService.createToken(response, principal);

        Cookie cookie = new Cookie(JwtTokenCst.HEADER_AUTHORIZATION, jwt);
        cookie.setMaxAge(6000);
        cookie.setPath(getDefaultTargetUrl());
        response.addCookie(cookie);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
