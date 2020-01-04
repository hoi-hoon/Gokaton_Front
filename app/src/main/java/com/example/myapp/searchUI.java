package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchUI extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.search_layout);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.search_rankbtn :
                        Intent intentA = new Intent(getApplicationContext(), rankUI.class);
                        startActivity(intentA);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.search_homebtn :
                        Intent intentB = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentB);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.search_postbtn :
                        Intent intentC = new Intent(getApplicationContext(), postUI.class);
                        startActivity(intentC);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                }
            }
        } ;
        ImageButton btnrank = (ImageButton) findViewById(R.id.search_rankbtn) ;
        btnrank.setOnClickListener(onClickListener) ;
        ImageButton btnhome = (ImageButton) findViewById(R.id.search_homebtn) ;
        btnhome.setOnClickListener(onClickListener) ;
        ImageButton btnpost = (ImageButton) findViewById(R.id.search_postbtn) ;
        btnpost.setOnClickListener(onClickListener) ;

        editText = (EditText)findViewById(R.id.search_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                switch(i) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), searchResultUI.class);
                        startActivityForResult(intent,1);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }
}
