package com.example.woniyoon.assignment10.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.woniyoon.assignment10.Activities.Main2Activity;
import com.example.woniyoon.assignment10.Interfaces.AsteriskPasswordTransformationMethod;
import com.example.woniyoon.assignment10.Interfaces.UserStorage;
import com.example.woniyoon.assignment10.Models.ProfileEntity;
import com.example.woniyoon.assignment10.Models.UserEntity;
import com.example.woniyoon.assignment10.R;
import com.example.woniyoon.assignment10.Retrofit.ApiUtils;
import com.example.woniyoon.assignment10.Retrofit.RetrofitService;
import com.example.woniyoon.assignment10.Validator.StringValidator;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {

    EditText registerFirstName, registerLastName, registerUsername, registerPassword, registerCountry;
    Button registerButton;
    StringValidator stringValidator = new StringValidator();
    RetrofitService mService;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerFirstName = view.findViewById(R.id.register_firstName);
        registerLastName = view.findViewById(R.id.register_lastName);
        registerCountry = view.findViewById(R.id.register_country);
        registerUsername = view.findViewById(R.id.register_username);
        registerPassword = view.findViewById(R.id.register_password);
        registerPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        registerButton = view.findViewById(R.id.register_register_button);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String givenId = registerUsername.getText().toString();
                String givenPw = registerPassword.getText().toString();

                if (!isUsernameValid(givenId) && !isPasswordValid(givenPw)) {
                    Toast.makeText(getActivity(), "Username & Password is not valid!", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValid(givenPw)) {
                    Toast.makeText(getActivity(), "Password is not valid!", Toast.LENGTH_SHORT).show();
                } else if (!isUsernameValid(givenId)) {
                    Toast.makeText(getActivity(), "Username is not valid!", Toast.LENGTH_SHORT).show();
                } else {

                    postUser(new ProfileEntity(registerFirstName.getText().toString()
                            , registerLastName.getText().toString()
                            , registerCountry.getText().toString()
                            , givenId, givenPw));
                }
            }
        });
    }


    public boolean isUsernameValid(String username) {
        Pattern pattern = Pattern.compile("^[0-9a-zA-Z_\\-]+@[.0-9a-zA-Z_\\-]+$");
        if (stringValidator.isValid(username, pattern)) {
            return true;
        }
        return false;
    }

    public boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("(?=([a-zA-Z0-9].*(\\W))|((\\W).*[a-zA-Z0-9])$).{8,12}");
        if (stringValidator.isValid(password, pattern)) {
            return true;
        }
        return false;
    }

    public void postUser(ProfileEntity profileEntity) {
        mService = ApiUtils.getSOService();
        Toast.makeText(getActivity(), "info sent!", Toast.LENGTH_SHORT).show();

        mService.postUser(profileEntity).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()) {
                    UserStorage.setUser(getContext(), response.body());
                    Intent intent = new Intent(getActivity(), Main2Activity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {
                    Log.d("RegisterFragment", Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    public void showErrorMessage() {
        Toast.makeText(getContext(), "error!", Toast.LENGTH_SHORT).show();
    }
}
