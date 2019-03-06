package com.geektech.notesapp.presentation.launch;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geektech.notesapp.App;
import com.geektech.notesapp.R;
import com.geektech.notesapp.presentation.intro.IntroActivity;
import com.geektech.notesapp.presentation.main.MainActivity;

public class LaunchActivity extends AppCompatActivity {

    private static String PREF_FIRST_LAUNCH = "first_launch";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(PREF_FIRST_LAUNCH, Context.MODE_PRIVATE);

        if (App.sharedStorage.get(PREF_FIRST_LAUNCH, true)) {
            IntroActivity.start(this);
            App.sharedStorage.set(PREF_FIRST_LAUNCH, false);
        } else {
            MainActivity.start(this);
        }
        finish();
    }
}
