package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

public class ExplanationsActivity extends AppCompatActivity {

    public static final String NO_OF_QUESTIONS = "com.gmail.fernandesi2244.crowdcs.NUMBER_OF_QUESTIONS";
    public static final String NO_RIGHT = "com.gmail.fernandesi2244.crowdcs.NUMBER_OF_QUESTIONS_RIGHT";

    private ArrayList<ParseObject> questionList;
    private ArrayList<String> answerList;
    private List<ParseObject> relevantQuestions;
    private int noOfQuestions;
    private int noRight;
    private int questionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanations);

        Intent receivedIntent = getIntent();
        questionList = (ArrayList<ParseObject>)(receivedIntent.getSerializableExtra(QuizActivity.QUESTIONS));
        answerList = (ArrayList<String>)(receivedIntent.getSerializableExtra(QuizActivity.ANSWERS));
        noOfQuestions = questionList.size();
        questionNo = 0;
        noRight = 0;

        updateScreen(questionList, questionNo);
    }

    public void updateScreen(List<ParseObject> relevantQuestionsList, int questionNumber) {
        if(questionNumber==noOfQuestions)
            displayResults();
        else {
            relevantQuestions = relevantQuestionsList;

            ParseObject current = relevantQuestionsList.get(questionNumber);

            TextView questionNumberDisplay = findViewById(R.id.questionNumberDisplay_Exp);
            questionNumberDisplay.setText("Question #" + (questionNumber + 1));

            TextView questionTextDisplay = findViewById(R.id.questionDisplay_Exp);
            questionTextDisplay.setText(current.getString("questionText"));

            TextView userAnswerDisplay = findViewById(R.id.yourAnswerDisplay_Exp);
            userAnswerDisplay.setText(answerList.get(questionNumber));

            TextView correctAnswerDisplay = findViewById(R.id.correctAnswerDisplay_Exp);
            correctAnswerDisplay.setText(current.getString("answerText"));

            TextView explanationDisplay = findViewById(R.id.explanationDisplay_Exp);
            explanationDisplay.setText(current.getString("explanation"));

            if(current.getString("answerText").equals(answerList.get(questionNumber)))
                noRight++;

        }
    }

    public void nextButtonPicked(View view) {
        updateScreen(relevantQuestions,++questionNo);
    }

    public void displayResults() {
        Intent displayResults = new Intent(this, DisplayResultsActivity.class);
        displayResults.putExtra(NO_OF_QUESTIONS, noOfQuestions);
        displayResults.putExtra(NO_RIGHT, noRight);
        startActivity(displayResults);
    }
}
