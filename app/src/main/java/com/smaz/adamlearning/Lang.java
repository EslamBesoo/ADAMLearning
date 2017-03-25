package com.smaz.adamlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.smaz.adamlearning.Util.LocaleHelper;

public class Lang extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView img_en = (ImageView)findViewById(R.id.img_en);
        ImageView img_ar = (ImageView)findViewById(R.id.img_ar);

        img_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Lang.this, "en");
                Intent i = new Intent(Lang.this,HomeActivity.class);
                startActivity(i);



            }
        });
        img_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocaleHelper.setLocale(Lang.this, "ar");
                Intent i = new Intent(Lang.this,HomeActivity.class);
                startActivity(i);

            }
        });




    }


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}