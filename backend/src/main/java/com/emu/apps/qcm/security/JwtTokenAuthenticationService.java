package com.emu.apps.qcm.security;

import io.jsonwebtoken.*;
import org.slf4j.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;

import javax.servlet.http.*;
import java.util.*;

import static java.util.Collections.*;

@Service
public class JwtTokenAuthenticationService {
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static final String SECRET = "ThisIsMySecret";

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    public String createToken(HttpServletResponse res, String username) {
        String jwt = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtTokenCst.HEADER_AUTHORIZATION);
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(JwtTokenCst.BEARER_TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            } catch (Exception e) {
                logger.warn("Error while parsing jwt: ", token);
            }

        }
        return null;
    }
}
