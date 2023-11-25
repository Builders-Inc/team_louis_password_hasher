package com.tekteam.hash.exceptions;


public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String s) {
        super("Unknown fatal error");
    }
}
