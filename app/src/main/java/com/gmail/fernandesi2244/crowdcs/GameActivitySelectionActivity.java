package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameActivitySelectionActivity extends AppCompatActivity {

    /**
     * DisplayMessageActivity uses this key to retrieve the value that was linked in the Intent.
     * It customary to use a key when sending a value to an Intent.
     */
    public static final String PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION = "com.gmail.fernandesi2244.crowdcs.LANGUAGE_ACTIVITY_SELECTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

    }

    public void goToContribute(View view) {
        Intent receivedIntent = getIntent();
        String language = receivedIntent.getStringExtra(LanguageSelectionActivity.PROGRAMMING_LANGUAGE);

        Intent sendIntent = new Intent(this, ContributeActivity.class);
        sendIntent.putExtra(PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION, language);
        startActivity(sendIntent);
    }

    public void goToQuiz(View view) {
        Intent receivedIntent = getIntent();
        String language = receivedIntent.getStringExtra(LanguageSelectionActivity.PROGRAMMING_LANGUAGE);

        Intent sendIntent = new Intent(this, QuizActivity.class);
        sendIntent.putExtra(PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION, language);
        startActivity(sendIntent);
    }
}
