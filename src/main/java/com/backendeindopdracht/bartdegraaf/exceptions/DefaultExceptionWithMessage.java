package com.backendeindopdracht.bartdegraaf.exceptions;

public class DefaultExceptionWithMessage extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DefaultExceptionWithMessage(String text) {
        super(text);
    }

}
