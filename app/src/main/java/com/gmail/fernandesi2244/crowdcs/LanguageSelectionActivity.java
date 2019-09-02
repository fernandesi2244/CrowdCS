package com.gmail.fernandesi2244.crowdcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LanguageSelectionActivity extends AppCompatActivity {

    /**
     * DisplayMessageActivity uses this key to retrieve the value that was linked in the Intent.
     * It customary to use a key when sending a value to an Intent.
     */
    public static final String PROGRAMMING_LANGUAGE = "com.gmail.fernandesi2244.crowdcs.LANGUAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
    }

    public void javaSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.javaString));
        startActivity(intent);
    }

    public void javascriptSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.javascriptString));
        startActivity(intent);
    }

    public void pythonSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.pythonString));
        startActivity(intent);
    }

    public void cPlusPlusSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.cPlusPlusString));
        startActivity(intent);
    }

    public void cSharpSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.cSharpString));
        startActivity(intent);
    }

    public void swiftSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.swiftString));
        startActivity(intent);
    }

    public void rubySelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.rubyString));
        startActivity(intent);
    }

    public void kotlinSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.kotlinString));
        startActivity(intent);
    }

    public void rustSelection(View view) {
        Intent intent = new Intent(this, GameActivitySelectionActivity.class);
        intent.putExtra(PROGRAMMING_LANGUAGE, getResources().getString(R.string.rustString));
        startActivity(intent);
    }

    public void goBack (View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
