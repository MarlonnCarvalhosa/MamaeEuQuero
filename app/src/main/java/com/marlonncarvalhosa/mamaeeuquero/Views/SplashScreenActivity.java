package com.marlonncarvalhosa.mamaeeuquero.Views;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.marlonncarvalhosa.mamaeeuquero.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_DISPLAY_LENGTH = 3000;

   // int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
                finish();
            }
        },SPLASH_DISPLAY_LENGTH);

    }


}
