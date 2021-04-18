package com.example.globoplay_desafio_mobile.viewmodels.account;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.globoplay_desafio_mobile.models.account.LoginModel;
import com.example.globoplay_desafio_mobile.models.account.UserModel;
import com.example.globoplay_desafio_mobile.repositories.AccountRepository;
import com.example.globoplay_desafio_mobile.response.account.LoginResponse;

public class AccountViewModel extends ViewModel {

    private AccountRepository accountRepository;

    //Constructor
    public  AccountViewModel(){
        accountRepository = AccountRepository.getInstance();
    }

    //Getter login response
    public MutableLiveData<LoginModel> getLoginResponse() {
        return accountRepository.getLoginResponse();
    }

    public MutableLiveData<UserModel> getDetailsUser() {
        return accountRepository.getDetailsUser();
    }

    //Setter
    public void setNullLoginResponse() {
        accountRepository.setNullLoginResponse();
    }

    // Call the method createToken in AccountRepository
    public void login(String username,String password){
        accountRepository.login(username,password);
    }

    // Call the method getDetailsUser in AccountRepository
    public void getDetailsUser(String sessionId){
        accountRepository.getDetailsUser(sessionId);
    }
}
