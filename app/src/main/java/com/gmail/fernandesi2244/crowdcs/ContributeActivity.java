package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Arrays;

public class ContributeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribute);

    }

    public void submitQuestion(View view) {
        Intent receivedIntent = getIntent();
        String language = receivedIntent.getStringExtra(GameActivitySelectionActivity.PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION);

        EditText question = (EditText) (findViewById(R.id.questionSubmissionBox));
        EditText choiceA = (EditText) (findViewById(R.id.answerChoiceA));
        EditText choiceB = (EditText) (findViewById(R.id.answerChoiceB));
        EditText choiceC = (EditText) (findViewById(R.id.answerChoiceC));
        EditText choiceD = (EditText) (findViewById(R.id.answerChoiceD));
        EditText answer = (EditText) (findViewById(R.id.answer));

        String questionString = question.getText().toString();
        String choiceAString = choiceA.getText().toString();
        String choiceBString = choiceB.getText().toString();
        String choiceCString = choiceC.getText().toString();
        String choiceDString = choiceD.getText().toString();
        String answerString = answer.getText().toString();

        if(!answerString.equals(choiceAString)&&!answerString.equals(choiceBString)&&!answerString.equals(choiceCString)&&!answerString.equals(choiceDString)){
            ////////////////////////////////////////////////////////////////
            //DEBUGGING TIME!
            //TextView testing = findViewById(R.id.debugBox);
            //testing.setText(answer.getText().toString());
            ////////////////////////////////////////////////////////////////
            Toast.makeText(getApplicationContext(), "Sorry, but the answer does not match any answer choice that you provided! Please try again.", Toast.LENGTH_LONG).show();
        } else {
            // Configure Query
            ParseObject questionObject = new ParseObject("Question");
            // Store an object
            questionObject.put("questionText", questionString);
            questionObject.put("answerText", answerString);
            questionObject.put("programmingLanguage", language);
            questionObject.put("whoSharedIt", ParseUser.getCurrentUser().getUsername());
            questionObject.addAllUnique("answerChoices", Arrays.asList(choiceAString, choiceBString, choiceCString, choiceDString));
            // Saving object
            questionObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // Success
                        Toast.makeText(getApplicationContext(), "Great! You've successfully uploaded a question!", Toast.LENGTH_LONG).show();
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        currentUser.put("totalQuestionsContributed",currentUser.getInt("totalQuestionsContributed")+1);
                        currentUser.saveInBackground();

                    } else {
                        // Error
                        Toast.makeText(getApplicationContext(), "Something happened :( Please try again or restart the app.", Toast.LENGTH_LONG).show();
                    }
                }
            });

            Intent goToProfile = new Intent(this, ProfileActivity.class);
            startActivity(goToProfile);
        }


    }
}
