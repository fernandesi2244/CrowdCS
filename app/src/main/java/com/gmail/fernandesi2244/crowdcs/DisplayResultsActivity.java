package com.gmail.fernandesi2244.crowdcs;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class DisplayResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        updateScreen();
    }

    public void updateScreen() {
        Intent receivedIntent = getIntent();
        int noCorrect = receivedIntent.getIntExtra(ExplanationsActivity.NO_RIGHT, 0);
        int totalQuestions = receivedIntent.getIntExtra(ExplanationsActivity.NO_OF_QUESTIONS, 0);
        String percentage = String.format("%.2f", totalQuestions == 0 ? (0) : (100.0 * noCorrect / totalQuestions));

        //Update TextViews
        TextView numCorrectDisplayBox = findViewById(R.id.numCorrectDisplay);
        numCorrectDisplayBox.setText("" + noCorrect);

        TextView numWrongDisplayBox = findViewById(R.id.numWrongDisplay);
        numWrongDisplayBox.setText("" + (totalQuestions - noCorrect));

        TextView percentDisplayBox = findViewById(R.id.scoreDisplay);
        if (Double.parseDouble(percentage) >= 70) {
            percentDisplayBox.setTextColor(Color.GREEN);
        } else {
            percentDisplayBox.setTextColor(Color.RED);
        }
        percentDisplayBox.setText(percentage + "%");

        updateOnlineStatistics(noCorrect, totalQuestions, percentage);

    }

    public void goToProfileScreen(View view) {
        Intent goToProfile = new Intent(this, ProfileActivity.class);
        startActivity(goToProfile);
    }

    public void updateOnlineStatistics(int noCorrect, int totalQuestions, String percentage) {
        double doublePercentage = Double.parseDouble(percentage);

        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.increment("noOfQuizzesTaken");
        try {
            double newPercentAverage = (currentUser.getDouble("AvgPercentCorrect") + doublePercentage) / currentUser.getInt("noOfQuizzesTaken");
            currentUser.put("AvgPercentCorrect", newPercentAverage);
        } catch (ArithmeticException e) {
            Toast.makeText(getApplicationContext(), "Something went wrong :( Please try again or restart the app.", Toast.LENGTH_LONG).show();
            if (currentUser.getInt("noOfQuizzesTaken") < 0)
                currentUser.put("noOfQuizzesTaken", 0);
        }

        currentUser.increment("totalQuestionsAnswered", totalQuestions);
        currentUser.increment("totalAnswersCorrect", noCorrect);

        currentUser.saveInBackground();

    }

}
