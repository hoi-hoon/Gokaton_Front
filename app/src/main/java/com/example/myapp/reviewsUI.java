package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
public class reviewsUI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.reviews_layout);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.reviews_searchbtn :
                        Intent intentA = new Intent(getApplicationContext(), searchUI.class);
                        startActivity(intentA);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.reviews_homebtn :
                        Intent intentB = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentB);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.reviews_postbtn :
                        Intent intentC = new Intent(getApplicationContext(), postUI.class);
                        startActivity(intentC);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.reviews_rankbtn :
                        Intent intentD = new Intent(getApplicationContext(), rankUI.class);
                        startActivity(intentD);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.reviews_backbtn :
                        Intent intent = new Intent();
                        intent.putExtra("result", "다봤어요..");
                        setResult(RESULT_OK, intent);
                        finish();
                        break ;
                }
            }
        } ;
        ImageButton btnsearch = (ImageButton) findViewById(R.id.reviews_searchbtn) ;
        btnsearch.setOnClickListener(onClickListener) ;
        ImageButton btnhome = (ImageButton) findViewById(R.id.reviews_homebtn) ;
        btnhome.setOnClickListener(onClickListener) ;
        ImageButton btnpost = (ImageButton) findViewById(R.id.reviews_postbtn) ;
        btnpost.setOnClickListener(onClickListener) ;
        ImageButton btnrank = (ImageButton) findViewById(R.id.reviews_rankbtn) ;
        btnrank.setOnClickListener(onClickListener) ;
        ImageView imageView = (ImageView) findViewById(R.id.reviews_backbtn);
        imageView.setOnClickListener(onClickListener);

        // imageView.setBackground(new ShapeDrawable(new OvalShape()));
        // imageView.setClipToOutline(true);
        // 이미지 동그랗게
    }
}
