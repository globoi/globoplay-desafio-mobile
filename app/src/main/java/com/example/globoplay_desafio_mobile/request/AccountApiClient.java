package com.example.globoplay_desafio_mobile.request;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.globoplay_desafio_mobile.models.account.LoginModel;
import com.example.globoplay_desafio_mobile.models.account.UserModel;
import com.example.globoplay_desafio_mobile.response.account.AuthenticationResponse;
import com.example.globoplay_desafio_mobile.response.account.LoginResponse;
import com.example.globoplay_desafio_mobile.response.account.SessionResponse;
import com.example.globoplay_desafio_mobile.response.account.UserResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountApiClient {

    private static AccountApiClient instance;

    private MutableLiveData<LoginModel> loginResponse;

    private MutableLiveData<UserModel> detailsUser;

    private LoginModel _loginResponse = new LoginModel();

    public static AccountApiClient getInstance() {
        if(instance==null){
            instance = new AccountApiClient();
        }
        return instance;
    }

    //Constructor
    private AccountApiClient(){
        loginResponse = new MutableLiveData<>();
        detailsUser = new MutableLiveData<>();
    }

    //Getter
    public MutableLiveData<LoginModel> getLoginResponse() {
        return loginResponse;
    }

    public MutableLiveData<UserModel> getDetailsUser() {
        return detailsUser;
    }

    //Setter
    public void setNullLoginResponse() {
       this.loginResponse.postValue(null);
    }


    public void createToken(String username, String password){
        //create token
        Call<AuthenticationResponse> callToken = Service.getClient().createRequestToken(Service.API_KEY);

        callToken.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if(response.isSuccessful()){
                    if(response.body() != null)
                        validadeLogin(username,password,response.body().getRequest_token());
                }else {
                    loginResponse.postValue(_loginResponse);
                    _loginResponse.setSucess(false);
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });

    }

    private void validadeLogin(String username,String password,String requestToken){

        //validate username and password
        Call<AuthenticationResponse> callValidateLoing = Service.getClient().validateWithLogin(username,password,requestToken,Service.API_KEY);

        callValidateLoing.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {

                if(response.isSuccessful()) {
                    if (response.body() != null)
                        createSession(requestToken);
                }else {
                    loginResponse.postValue(_loginResponse);
                    _loginResponse.setSucess(false);
                }
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

    private void createSession(String requestToken){
        //create session
        Call<SessionResponse> callSession = Service.getClient().createSession(requestToken,Service.API_KEY);

        callSession.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {

                if(response.isSuccessful()){
                    if(response.body() != null)
                        getAccountID(response.body().getSession_id());
                }else {
                    loginResponse.postValue(_loginResponse);
                    _loginResponse.setSucess(false);
                }
            }

            @Override
            public void onFailure(Call<SessionResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

    private void getAccountID(String sessionId) {

        //get account id
        Call<LoginResponse> callSession = Service.getClient().getAccountId(sessionId,Service.API_KEY);

        callSession.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        _loginResponse.setId(response.body().getId()); //get account id
                        _loginResponse.setSessionId(sessionId);
                        _loginResponse.setSucess(true);
                    }
                }
                else
                    _loginResponse.setSucess(false);

                loginResponse.postValue(_loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
                _loginResponse.setSucess(false);
            }
        });
    }

    public void getDetailsUser(String sessionId){

        Call<UserResponse> callSession = Service.getClient().getDetails(sessionId,Service.API_KEY);

        callSession.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        UserModel userModel = new UserModel(response.body().getUsername(),response.body().getAvatar().getAvatarTmdbResponse().getAvatar_path());

                        detailsUser.postValue(userModel);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }
}
