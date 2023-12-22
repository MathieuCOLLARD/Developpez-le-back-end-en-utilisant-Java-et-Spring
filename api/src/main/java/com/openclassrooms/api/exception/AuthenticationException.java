package com.openclassrooms.api.exception;

public class AuthenticationException extends RuntimeException
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AuthenticationException(String s)
    {
        super(s);
    }
}