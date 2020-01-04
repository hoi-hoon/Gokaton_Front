package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class searchResultUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.searchresult_layout);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sresult_searchbtn :
                        Intent intentA = new Intent(getApplicationContext(), searchUI.class);
                        startActivity(intentA);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.sresult_homebtn :
                        Intent intentB = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentB);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.sresult_postbtn :
                        Intent intentC = new Intent(getApplicationContext(), postUI.class);
                        startActivity(intentC);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                }
            }
        } ;
        ImageButton btnsearch = (ImageButton) findViewById(R.id.sresult_searchbtn) ;
        btnsearch.setOnClickListener(onClickListener) ;
        ImageButton btnhome = (ImageButton) findViewById(R.id.sresult_homebtn) ;
        btnhome.setOnClickListener(onClickListener) ;
        ImageButton btnpost = (ImageButton) findViewById(R.id.sresult_postbtn) ;
        btnpost.setOnClickListener(onClickListener) ;
    }
}
