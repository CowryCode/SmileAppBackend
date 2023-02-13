package com.cowrycode.smileapp.services;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    String getIdentifier(HttpServletRequest request);
    Long getIdentifierLong(HttpServletRequest request);
}
