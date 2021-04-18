package com.example.globoplay_desafio_mobile.ui.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.databinding.LoginActivityBinding;
import com.example.globoplay_desafio_mobile.models.account.LoginModel;
import com.example.globoplay_desafio_mobile.response.account.LoginResponse;
import com.example.globoplay_desafio_mobile.ui.movieList.MainListActivity;
import com.example.globoplay_desafio_mobile.viewmodels.account.AccountViewModel;

public class LoginActivity extends AppCompatActivity {

    //ViewBinding
    private LoginActivityBinding loginActivityBinding;

    //ViewModel
    private AccountViewModel accountViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginActivityBinding= LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(loginActivityBinding.getRoot());

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        //Calling the observer
       observeAccountChange();

        //click login button
        loginActivityBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // EditText edUsername = loginActivityBinding.edUsername;
             //   EditText edPassword = loginActivityBinding.edPassword;


             /* if(!edUsername.getText().toString().equals("") && !edPassword.getText().toString().equals("")) {
                    loginActivityBinding.progressBarApp.setVisibility(View.VISIBLE);
                    loginActivityBinding.btn.setVisibility(View.GONE);
                    accountViewModel.login(edUsername.getText().toString(), edPassword.getText().toString());
                }*/

                goToListMovies();
            }
        });
    }

    //observer
    private void observeAccountChange() {
        accountViewModel.getLoginResponse().observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginResponse) {
               if(loginResponse != null) {
                    if (loginResponse.isSucess()) {
                        saveInfoUser(loginResponse);
                    } else
                        Toast.makeText(getApplicationContext(), "Error signing in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //save info user
    private void saveInfoUser(LoginModel loginResponse) {
        SharedPreferences.Editor editor = getSharedPreferences("detailsUser", MODE_PRIVATE).edit();
        editor.putString("accountId", loginResponse.getId());
        editor.putString("sessionId", loginResponse.getSessionId());
        editor.apply();

        goToListMovies();
    }

    //go to movie list
    private void goToListMovies(){
        accountViewModel.setNullLoginResponse();
        loginActivityBinding.progressBarApp.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, MainListActivity.class);
        startActivity(intent);
    }
}
