package com.brunobarchesi.usuario.infrastucture.execeptions;

public class ConflictExeception extends RuntimeException{

    public ConflictExeception(String mensagem){
        super(mensagem);
    }

    public ConflictExeception(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }


}
