package com.rameshwar.motivationalquotesenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){

            public void run(){
                try {
                    sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                finally {
                    Intent intent = new Intent(SplashScreenActivity.this,StartActivity.class);
                    startActivity(intent);

                }finish();
            }

        };thread.start();

        getSupportActionBar().hide();
    }
}