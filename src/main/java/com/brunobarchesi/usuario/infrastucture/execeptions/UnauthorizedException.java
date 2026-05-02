package com.brunobarchesi.usuario.infrastucture.execeptions;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
