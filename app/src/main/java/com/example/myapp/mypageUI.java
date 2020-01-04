package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class mypageUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mypage_layout);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.mypage_searchbtn :
                        Intent intentA = new Intent(getApplicationContext(), searchUI.class);
                        startActivity(intentA);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.mypage_rankbtn :
                        Intent intentB = new Intent(getApplicationContext(), rankUI.class);
                        startActivity(intentB);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.mypage_postbtn :
                        Intent intentC = new Intent(getApplicationContext(), postUI.class);
                        startActivity(intentC);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.mypage_homebtn :
                        Intent intentD = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentD);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                }
            }
        } ;
        ImageButton btnsearch = (ImageButton) findViewById(R.id.mypage_searchbtn) ;
        btnsearch.setOnClickListener(onClickListener) ;
        ImageButton btnrank = (ImageButton) findViewById(R.id.mypage_rankbtn) ;
        btnrank.setOnClickListener(onClickListener) ;
        ImageButton btnpost = (ImageButton) findViewById(R.id.mypage_postbtn) ;
        btnpost.setOnClickListener(onClickListener) ;
        ImageButton btnhome = (ImageButton) findViewById(R.id.mypage_homebtn) ;
        btnhome.setOnClickListener(onClickListener) ;
    }
}
