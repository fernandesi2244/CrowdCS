package com.gmail.fernandesi2244.crowdcs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        updateProfile();

    }

    public void updateProfile() {
        ParseUser currentUser = ParseUser.getCurrentUser();

        String username = currentUser.getUsername();
        TextView usernameBox = findViewById(R.id.displayUsername);
        usernameBox.setText("Username: "+username);


        int totalQuestionsAnswered = currentUser.getInt("totalQuestionsAnswered");
        TextView totalAnsweredBox = findViewById(R.id.displayQuestionsAnswered);
        totalAnsweredBox.setText("Total Number of Questions Answered: "+totalQuestionsAnswered);

        int totalAnswersCorrect = currentUser.getInt("totalAnswersCorrect");
        TextView answeredCorrectBox = findViewById(R.id.displayTotalCorrect);
        answeredCorrectBox.setText("Total Number of Answers Correct: "+totalAnswersCorrect);

        double avgPercentCorrect = currentUser.getInt("noOfQuizzesTaken")==0? 0: currentUser.getDouble("totalPoints")/currentUser.getInt("noOfQuizzesTaken");
        TextView percentCorrectBox = findViewById(R.id.displayAvgPercentCorrect);
        percentCorrectBox.setText("Average Percentage Correct: "+String.format("%.2f%%", avgPercentCorrect));

        int totalQuestionsContributed = currentUser.getInt("totalQuestionsContributed");
        TextView questionsContributedBox = findViewById(R.id.displayQuestionsContributed);
        questionsContributedBox.setText("Total Number of Questions Contributed: "+totalQuestionsContributed);

    }

    public void goToSelectionScreen(View view) {
        Intent intent = new Intent(this, LanguageSelectionActivity.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        ParseUser.logOut();
        Toast.makeText(getApplicationContext(),"Come back soon!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
