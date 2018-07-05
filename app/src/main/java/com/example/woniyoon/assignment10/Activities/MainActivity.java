package com.example.woniyoon.assignment10.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.woniyoon.assignment10.Fragments.LoginFragment;
import com.example.woniyoon.assignment10.Fragments.RegisterFragment;
import com.example.woniyoon.assignment10.Interfaces.UserStorage;
import com.example.woniyoon.assignment10.R;

public class MainActivity extends AppCompatActivity {

    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.main_login_button);
        registerButton = findViewById(R.id.main_register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main1_container, LoginFragment.newInstance()).addToBackStack("loginFrag").commit();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main1_container, RegisterFragment.newInstance()).addToBackStack("registrationFrag").commit();
            }
        });
    }
}
