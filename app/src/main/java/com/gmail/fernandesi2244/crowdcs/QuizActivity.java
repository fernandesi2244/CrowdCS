package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ArrayList<String> chosenAnswers;
    private List<ParseObject> relevantQuestions;
    private int questionNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent receivedIntent = getIntent();
        String language = receivedIntent.getStringExtra(GameActivitySelectionActivity.PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION);

        chosenAnswers = new ArrayList<>();
        relevantQuestions = new ArrayList<>();
        questionNo = 0;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.whereEqualTo("programmingLanguage", language);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> relevantQuestionList, ParseException e) {
                if (e == null) {
                    updateScreen(relevantQuestionList, questionNo);
                } else {
                    // Something is wrong
                    Toast.makeText(getApplicationContext(),"Something wrong happened. Perhaps there are no questions in the desired category? Why don't you make some of your own?", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public void updateScreen(List<ParseObject> relevantQuestionsList, int questionNumber) {
            if(questionNumber==2)           //Change this later to represent slider position and taking into account edge cases with # of questions left
                return;                     //Change this later to start showing correct answers
            relevantQuestions = relevantQuestionsList;

            ParseObject current = relevantQuestionsList.get(questionNumber);

            TextView questionNumberDisplay = findViewById(R.id.questionNumber);
            questionNumberDisplay.setText("Question #"+(questionNumber+1));

            TextView questionTextDisplay = findViewById(R.id.displayQuestionText);
            questionTextDisplay.setText(current.getString("questionText"));

            List<String> answerChoiceList = current.getList("answerChoices");

            Button b1 = findViewById(R.id.buttonForChoiceA);
            b1.setText(answerChoiceList.get(0));

            Button b2 = findViewById(R.id.buttonForChoiceB);
            b2.setText(answerChoiceList.get(1));

            Button b3 = findViewById(R.id.buttonForChoiceC);
            b3.setText(answerChoiceList.get(2));

            Button b4 = findViewById(R.id.buttonForChoiceD);
            b4.setText(answerChoiceList.get(3));

    }

    public void answerChoicePicked(View view) {
        /*Button btn = (Button)(findViewById(R.id.buttonForChoiceA));
        String btnString = btn.getText().toString();*/
        Button btn = (Button)view;
        String btnString = btn.getText().toString();
        chosenAnswers.add(btnString);

        updateScreen(relevantQuestions,++questionNo);
    }

}
