package com.cowrycode.smileapp.services;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public Long getIdentifier(HttpServletRequest request) {
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
