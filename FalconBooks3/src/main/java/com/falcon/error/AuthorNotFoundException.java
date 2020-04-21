package com.falcon.error;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(String authname) {
        super("Author id not found : " + authname);
    }

}
