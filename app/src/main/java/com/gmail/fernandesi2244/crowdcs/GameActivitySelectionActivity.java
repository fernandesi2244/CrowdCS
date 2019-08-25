package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameActivitySelectionActivity extends AppCompatActivity {

    /**
     * DisplayMessageActivity uses this key to retrieve the value that was linked in the Intent.
     * It customary to use a key when sending a value to an Intent.
     */
    public static final String PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION = "com.gmail.fernandesi2244.crowdcs.LANGUAGE_ACTIVITY_SELECTION";
    public static final String NUMBER_QUESTIONS = "com.gmail.fernandesi2244.crowdcs.NUMBER_OF_QUESTIONS";

    private static SeekBar seekbar;
    private static TextView caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);
        updateSeekBarCaption();
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

        SeekBar questionSeekBar = (SeekBar)(findViewById(R.id.seekBar));

        Intent sendIntent = new Intent(this, QuizActivity.class);
        sendIntent.putExtra(PROGRAMMING_LANGUAGE_ACTIVITY_SELECTION, language);
        sendIntent.putExtra(NUMBER_QUESTIONS, questionSeekBar.getProgress());
        startActivity(sendIntent);
    }

    public void updateSeekBarCaption(){
        seekbar = (SeekBar) (findViewById(R.id.seekBar));
        caption = (TextView) (findViewById(R.id.howManyQuestionsCurrently));
        caption.setText(seekbar.getProgress()+" questions");

        seekbar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressAmount;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressAmount = progress;
                        caption.setText(progress+" questions");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        caption.setText(progressAmount + " questions");
                    }
                }
        );
    }
}
