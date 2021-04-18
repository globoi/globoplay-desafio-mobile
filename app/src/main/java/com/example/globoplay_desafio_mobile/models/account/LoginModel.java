package com.example.globoplay_desafio_mobile.models.account;

public class LoginModel {

    private String id ;
    private String sessionId ;
    private boolean sucess;

    //Getters
    public String getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public boolean isSucess() {
        return sucess;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

}
