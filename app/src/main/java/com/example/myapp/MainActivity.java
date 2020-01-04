package com.example.myapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import android.graphics.Color;
import org.w3c.dom.Text;
import android.view.MotionEvent;
public class MainActivity extends AppCompatActivity {
    private int ImageCount = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.home_layout);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.home_searchbtn:
                        Intent intentA = new Intent(getApplicationContext(), searchUI.class);
                        startActivity(intentA);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break;
                    case R.id.home_rankbtn:
                        Intent intentB = new Intent(getApplicationContext(), rankUI.class);
                        startActivity(intentB);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break;
                    case R.id.home_postbtn:
                        Intent intentC = new Intent(getApplicationContext(), postUI.class);
                        startActivityForResult(intentC, 1);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break;
                    case R.id.home_mypagebtn:
                        Intent intentD = new Intent(getApplicationContext(), mypageUI.class);
                        startActivityForResult(intentD, 1);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break;
                }
            }
        };

        ImageButton btnsearch = (ImageButton) findViewById(R.id.home_searchbtn);
        btnsearch.setOnClickListener(onClickListener);
        ImageButton btnrank = (ImageButton) findViewById(R.id.home_rankbtn);
        btnrank.setOnClickListener(onClickListener);
        ImageButton btnpost = (ImageButton) findViewById(R.id.home_postbtn);
        btnpost.setOnClickListener(onClickListener);
        Button btnmypage = (Button) findViewById(R.id.home_mypagebtn);
        btnmypage.setOnClickListener(onClickListener);

        // 토큰 확인용 임시. 추후 삭제 요망
        Button tokenBtn = (Button) findViewById(R.id.token_chk_btn);
        tokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 토큰 가져오는 코드
                SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
                String token = sp.getString("TokenCode", "");
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, "Result: " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            } else {   // RESULT_CANCEL
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
//        } else if (requestCode == REQUEST_ANOTHER) {
//            ...
        }
    }
}
