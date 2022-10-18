package com.cowrycode.smileapp.services;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    Long getIdentifier(HttpServletRequest request);
}
