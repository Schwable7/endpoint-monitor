package com.example.endpointmonitoring.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

import static com.example.endpointmonitoring.jwt.SecurityConstants.SECRET;

public class JWTFilter extends GenericFilterBean {

    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            final String authHeader = request.getHeader("authorization");
            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                filterChain.doFilter(request, response);
            } else {
                if(authHeader == null || !authHeader.startsWith("Bearer ")){
                    throw new AuthenticationException("Missing or invalid Authorization header");
                }
            }
            final String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print("Unauthorized");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }

    }
}