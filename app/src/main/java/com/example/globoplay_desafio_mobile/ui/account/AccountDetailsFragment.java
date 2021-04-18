package com.example.globoplay_desafio_mobile.ui.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.databinding.FragmentAccountDetailsBinding;
import com.example.globoplay_desafio_mobile.models.account.UserModel;
import com.example.globoplay_desafio_mobile.viewmodels.account.AccountViewModel;

import static android.content.Context.MODE_PRIVATE;

public class AccountDetailsFragment extends Fragment {

    //ViewBinding
    private FragmentAccountDetailsBinding fragmentAccountDetailsBinding;

    //ViewModel
    private AccountViewModel accountViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentAccountDetailsBinding = FragmentAccountDetailsBinding.inflate(inflater,container,false);

        accountViewModel = new ViewModelProvider(requireActivity()).get(AccountViewModel.class);

        //Calling the observer
        observeDetailsChange();

        //get account id
        SharedPreferences prefs = getActivity().getSharedPreferences("detailsUser", MODE_PRIVATE);
        String sessionId = prefs.getString("sessionId","");

        accountViewModel.getDetailsUser(sessionId);

        //btn logout
        fragmentAccountDetailsBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        return fragmentAccountDetailsBinding.getRoot();
    }

    //observer
    private void observeDetailsChange() {
        accountViewModel.getDetailsUser().observe(getViewLifecycleOwner(), new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                if(userModel != null)
                {
                    Glide.with(fragmentAccountDetailsBinding.image).load("https://image.tmdb.org/t/p/w200" +userModel.getAvatar()).placeholder(R.drawable.movie).into(fragmentAccountDetailsBinding.image);
                    fragmentAccountDetailsBinding.usermane.setText(userModel.getUsername());
                }
            }
        });
    }

    private void logout(){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("detailsUser", MODE_PRIVATE).edit();
        editor.putString("accountId", "");
        editor.putString("sessionId", "");
        editor.apply();

        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }
}
