package com.project.portfolio.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationsFilters extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationsFilters.class);

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.debug("doFilterInternal:::");

        try {

            String jwt = parseJwt(request);
            logger.debug("jwt:{}",jwt);

            if (jwt != null && jwtUtils.validateToken(jwt)) {
                logger.debug("jwt is valid {}::",jwt);

                String userId = jwtUtils.getUserIdFromToken(jwt);
                logger.debug("userId is {}::",userId);


                List<String> roles=parseClaims(jwt);

                List<GrantedAuthority> permission=List.of();

                if (roles!=null) {
                    permission=  roles.stream()
                            .map(role->(GrantedAuthority)new SimpleGrantedAuthority("ROLE_"+role))
                            .toList();
                }
                logger.debug("Permissiom:: {}",permission);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId,
                                null,
                                permission);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.debug("SecurityContextHolder:::{}"+authentication);


            }

            filterChain.doFilter(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    private String parseJwt(HttpServletRequest request){
        return jwtUtils.getJwtFromHeader(request);

    }


    private List<String> parseClaims(String jwt){
        //return (List<String>) jwtUtils.getAllCalims(jwt);
        Claims allCalims = jwtUtils.getAllCalims(jwt);
        List<String> claims = allCalims.get("roles",List.class);
        logger.debug("Claims:  {}",claims);
        return claims;
    }


}
