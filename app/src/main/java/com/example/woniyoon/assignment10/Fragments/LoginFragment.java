package com.example.woniyoon.assignment10.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woniyoon.assignment10.Activities.Main2Activity;
import com.example.woniyoon.assignment10.Activities.MainActivity;
import com.example.woniyoon.assignment10.Interfaces.UserStorage;
import com.example.woniyoon.assignment10.Models.LoginEntity;
import com.example.woniyoon.assignment10.Models.UserEntity;
import com.example.woniyoon.assignment10.R;
import com.example.woniyoon.assignment10.Retrofit.ApiUtils;
import com.example.woniyoon.assignment10.Retrofit.RetrofitService;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    EditText loginUsername, loginPassword;
    Button loginButton;
    RetrofitService mService;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginUsername = view.findViewById(R.id.login_username);
        loginPassword = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUser(new LoginEntity(loginUsername.getText().toString(), loginPassword.getText().toString()));
            }
        });
    }

    public void getUser(LoginEntity loginEntity) {
        mService = ApiUtils.getSOService();

        mService.getLogin(loginEntity).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.body().getUserId() == -1) {
                    Toast.makeText(getContext(), "not existing user!", Toast.LENGTH_SHORT).show();

                } else if (response.body() != null) {

                    UserStorage.setUser(getContext(), response.body());
                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    startActivity(intent);
                    getActivity().finish();

                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                Toast.makeText(getContext(), "failed" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
