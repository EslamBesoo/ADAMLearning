package com.smaz.adamlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(5 * 1000);


                    Intent i = new Intent(SplashScreen.this,Lang.class);
                    startActivity(i);


                } catch (Exception e) {

                }
            }
        };


        background.start();

    }
    @Override
    public void onBackPressed() {
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.gc();
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.gc();
    }


}