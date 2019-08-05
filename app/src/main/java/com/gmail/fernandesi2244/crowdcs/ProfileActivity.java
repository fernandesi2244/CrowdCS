package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;

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

        double avgPercentCorrect = currentUser.getDouble("AvgPercentCorrect");
        TextView percentCorrectBox = findViewById(R.id.displayAvgPercentCorrect);
        percentCorrectBox.setText("Average Percentage Correct: "+avgPercentCorrect*100+"%");

        int totalQuestionsContributed = currentUser.getInt("totalQuestionsContributed");
        TextView questionsContributedBox = findViewById(R.id.displayQuestionsContributed);
        questionsContributedBox.setText("Total Number of Questions Contributed: "+totalQuestionsContributed);



        /*ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("objectId", "9CeHVIvQQ3");
        //query.whereEqualTo("objectId", ParseUser.getCurrentUser().getObjectId());

        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject player, ParseException e) {
                if (e == null) {
                    String username = player.getString("username");
                    TextView usernameBox = findViewById(R.id.displayUsername);
                    usernameBox.setText(username);
                    //int yearOfBirth = player.getInt("yearOfBirth");
                } else {
                    // Something is wrong
                }
            }
        });*/

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
