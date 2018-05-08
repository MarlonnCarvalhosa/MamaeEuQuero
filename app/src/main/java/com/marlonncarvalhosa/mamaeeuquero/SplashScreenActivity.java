package com.marlonncarvalhosa.mamaeeuquero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        IniciarSplash();
    }

    public void IniciarSplash() {

        setContentView(R.layout.activity_splash_screen);

        new Thread (new Runnable() {

            @Override
            public void run() {

                counter++;

                try {

                    while (counter == 0 || counter <= 3) {

                        Thread.sleep(1000);
                        counter++;

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (counter == 4){

                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

                    startActivity(intent);

                    counter++;

                }

            }
        }).start();

         // Tirar para aparecer a SplashScreen

    }

}
