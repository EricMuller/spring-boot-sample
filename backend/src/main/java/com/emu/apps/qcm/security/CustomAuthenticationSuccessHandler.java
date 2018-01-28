package com.emu.apps.qcm.security;

import com.emu.apps.qcm.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenAuthenticationService jwtTokenAuthenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Profile principal = (Profile) authentication.getPrincipal();
        String jwt = jwtTokenAuthenticationService.createToken(response, principal.getFullName());

        Cookie cookie = new Cookie(JwtTokenCst.HEADER_AUTHORIZATION, jwt);
        cookie.setMaxAge(6000);
        cookie.setPath(getDefaultTargetUrl());
        response.addCookie(cookie);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
