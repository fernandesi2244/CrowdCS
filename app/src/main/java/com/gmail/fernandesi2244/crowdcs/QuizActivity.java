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

    public static final String QUESTIONS = "com.gmail.fernandesi2244.crowdcs.QUESTION_VALUES";
    public static final String ANSWERS = "com.gmail.fernandesi2244.crowdcs.ANSWER_VALUES";
    public static final String NO_OF_QUESTIONS_CHOSEN = "com.gmail.fernandesi2244.crowdcs.NUMBER_OF_QUESTIONS_CHOSEN";

    private ArrayList<String> chosenAnswers;
    private List<ParseObject> relevantQuestions;
    private int questionNo;
    private int noOfQuestions;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent receivedIntent = getIntent();
        language = receivedIntent.getStringExtra(GameActivitySelectionActivity.PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION);
        noOfQuestions = receivedIntent.getIntExtra(GameActivitySelectionActivity.NUMBER_QUESTIONS, 5);

        chosenAnswers = new ArrayList<>();
        relevantQuestions = new ArrayList<>();
        questionNo = 0;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
        query.whereEqualTo("programmingLanguage", language);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> relevantQuestionList, ParseException e) {
                if (e == null) {
                    if(noOfQuestions>relevantQuestionList.size()) {
                        backToGameActivitySelection();
                        return;
                    }
                    scramble(relevantQuestionList);
                    updateScreen(relevantQuestionList, questionNo);
                } else {
                    // Something is wrong
                    Toast.makeText(getApplicationContext(),"Something wrong happened. Perhaps there are no questions in the desired category? Why don't you make some of your own?", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    public void updateScreen(List<ParseObject> relevantQuestionsList, int questionNumber) {
            if(questionNumber==noOfQuestions)
                displayCorrectAnswers();
            else {
                relevantQuestions = relevantQuestionsList;

                ParseObject current = relevantQuestionsList.get(questionNumber);

                TextView questionNumberDisplay = findViewById(R.id.questionNumber);
                questionNumberDisplay.setText("Question #" + (questionNumber + 1));

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
    }

    public void answerChoicePicked(View view) {
        /*Button btn = (Button)(findViewById(R.id.buttonForChoiceA));
        String btnString = btn.getText().toString();*/
        Button btn = (Button)view;
        String btnString = btn.getText().toString();
        chosenAnswers.add(btnString);

        updateScreen(relevantQuestions,++questionNo);
    }

    public void backToGameActivitySelection() {
        Toast.makeText(getApplicationContext(), "Sorry, but the number of questions you have chosen exceeds that of the number of questions in the online database for the "+language+" language. Try a different language or make some of your own "+language+" questions!", Toast.LENGTH_LONG).show();
        Intent goBack = new Intent(this, LanguageSelectionActivity.class);
        startActivity(goBack);
    }

    public void displayCorrectAnswers() {
        ArrayList<ParseObject> listCopy = new ArrayList<ParseObject>();
        for(ParseObject current: relevantQuestions) {
            listCopy.add(current);
        }

        Intent goToProfile = new Intent(this, ExplanationsActivity.class);
        goToProfile.putExtra(QUESTIONS, listCopy);
        goToProfile.putExtra(ANSWERS, chosenAnswers);
        goToProfile.putExtra(NO_OF_QUESTIONS_CHOSEN, noOfQuestions);
        startActivity(goToProfile);
    }

    public void scramble(List<ParseObject> relevantQuestionsList) {
        List<ParseObject> copy = new ArrayList<>();
        for(ParseObject current: relevantQuestionsList) {
            copy.add(current);
        }
        relevantQuestionsList.clear();
        while(!copy.isEmpty()) {
            relevantQuestionsList.add(copy.remove((int)(Math.random()*copy.size())));
        }
    }

}
