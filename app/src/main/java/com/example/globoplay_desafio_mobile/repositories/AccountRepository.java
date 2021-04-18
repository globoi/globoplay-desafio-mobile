package com.example.globoplay_desafio_mobile.repositories;

import androidx.lifecycle.MutableLiveData;
import com.example.globoplay_desafio_mobile.models.account.LoginModel;
import com.example.globoplay_desafio_mobile.models.account.UserModel;
import com.example.globoplay_desafio_mobile.request.AccountApiClient;
import com.example.globoplay_desafio_mobile.response.account.LoginResponse;

public class AccountRepository {

    private static AccountRepository instance;

    private AccountApiClient accountApiClient;

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;

    }

    //Constructor
    private AccountRepository(){
        accountApiClient = AccountApiClient.getInstance();
    }

    //Getter login response
    public MutableLiveData<LoginModel> getLoginResponse() {
        return accountApiClient.getLoginResponse();
    }

    public MutableLiveData<UserModel> getDetailsUser() {
        return accountApiClient.getDetailsUser();
    }

    //Setter
    public void setNullLoginResponse() {
        accountApiClient.setNullLoginResponse();
    }

    // Call the method createToken in AccountApiClient
    public void login(String username,String password){
        accountApiClient.createToken(username,password);
    }

    // Call the method getDetailsUser in AccountApiClient
    public void getDetailsUser(String sessionId){
        accountApiClient.getDetailsUser(sessionId);
    }
}
