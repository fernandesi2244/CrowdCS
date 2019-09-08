package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void launchSignIn(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void logIn(View view) {
        EditText username = (EditText) (findViewById(R.id.usernameBox));
        EditText password = (EditText) (findViewById(R.id.passwordBox));

        final String USERNAME_STRING = username.getText().toString();

        ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    Toast.makeText(getApplicationContext(), "Successful Login. Welcome back " + USERNAME_STRING + "!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                } else {
                    ParseUser.logOut();
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
