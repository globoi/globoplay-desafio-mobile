package com.example.globoplay_desafio_mobile.response.account;

public class AuthenticationResponse {

    private String request_token;
    private boolean success;

    public String getRequest_token() {
        return request_token;
    }

    public boolean isSuccess() {
        return success;
    }
}
