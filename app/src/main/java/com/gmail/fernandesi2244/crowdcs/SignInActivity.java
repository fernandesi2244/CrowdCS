package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.AlertDialog;


public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void signIn(View view) {
        ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms
        EditText username = (EditText)(findViewById(R.id.signInUsername));
        EditText password = (EditText)(findViewById(R.id.signInPassword));
        EditText passwordConfirm = (EditText) (findViewById(R.id.signInConfirmPassword));

        if(password.getText().toString().equals(passwordConfirm.getText().toString())) {
            final String USERNAME_STRING = username.getText().toString();
            user.setUsername(USERNAME_STRING);
            user.setPassword(password.getText().toString());
            //user.setAvgPercentCorrect(0);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        //alertDisplayer("Successful Sign Up!", "Welcome " +  USERNAME_STRING +"!");
                        Toast.makeText(getApplicationContext(), "Successful Sign Up! Welcome "+USERNAME_STRING+"!", Toast.LENGTH_LONG).show();
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        currentUser.put("AvgPercentCorrect",0);
                        currentUser.put("totalQuestionsAnswered",0);
                        currentUser.put("totalAnswersCorrect",0);
                        currentUser.put("totalQuestionsContributed", 0);
                        Intent myIntent = new Intent(SignInActivity.this, ProfileActivity.class);
                        startActivity(myIntent);
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(SignInActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "The passwords do not match, please try again.", Toast.LENGTH_LONG).show();
        }
    }

    /*private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    } */
}
