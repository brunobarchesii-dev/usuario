package com.brunobarchesi.usuario.infrastucture.execeptions;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable causa){
        super(message, causa);
    }
}
