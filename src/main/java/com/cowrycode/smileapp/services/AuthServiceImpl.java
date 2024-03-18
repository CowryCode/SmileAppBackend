package com.cowrycode.smileapp.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String getIdentifier(HttpServletRequest request) {
        try{
            String header = request.getHeader("Authorization" );
            String token;
            if(header.startsWith("Bearer ")){
                token = header.substring(7).trim();
                return token;
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Long getIdentifierLong(HttpServletRequest request) {
        try{
            String header = request.getHeader("Authorization" );
            String token;
            if(header.startsWith("Bearer ")){
                token = header.substring(7).trim();
                return Long.valueOf(token);
            }else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
