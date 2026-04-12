package com.project.portfolio.security;


import com.project.portfolio.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private String jwtSecret="YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private int jwtExpirationInMs=172800000; //48H
    private Date jwtIssuedAt=new Date(new Date().getTime()-jwtExpirationInMs);
    private Date jwtExpiresAt=new Date(new Date().getTime() + jwtExpirationInMs);

    /*
    *  private  int jwtExpirationInMs_48H=172800000; //48H
    private  int jwtExpirationInMs_1M=60000; //1minut
    private  int jwtExpirationInMs_5M=172800000; //5m
    private  int jwtExpirationInMs_24H=86400000; /
    * */

    /**
     * AUTHORIZATION Beare<TOKE>  sending in this
     * formte in backend
     */

    public String getJwtFromHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;

    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /***
     * Genrate Token
     * @param user
     * @return
     */
    public String genrateToken( Users user){
        String role = user.getRole().name();
        return Jwts
                .builder()
                .subject(user.getId())
                .claim("roles", List.of(role))
                .issuedAt(jwtIssuedAt)
                .expiration(jwtExpiresAt)
                .signWith(key())
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(token);

        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }


    public String getUserIdFromToken(String jwt) {
        return  Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseClaimsJws(jwt)
                .getPayload()
                .getSubject();
    }

    public Claims getAllCalims(String jwt) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseClaimsJws(jwt)
                .getPayload();

    }


}
