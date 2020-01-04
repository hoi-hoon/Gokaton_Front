package com.example.myapp;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

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

        String url = "http://52.79.226.55:8000/api/auth/me";

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();

        // 토큰 확인용 임시. 추후 삭제 요망
        Button tokenBtn = (Button) findViewById(R.id.token_chk_btn);
        tokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // SharedPreferences 확인 코드
                SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
                // String token = sp.getString("TokenCode", "");
                String token = sp.getString("id", "");
                token = token + sp.getString("email", "");
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

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {
            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {
            String result; // 요청 결과를 저장할 변수.
            SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
            String token = sp.getString("TokenCode", "");
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values,"GET",token);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s!=null) {
                User user = new User();
                try {
                    JSONObject jObj = new JSONObject(s);
                    Log.i("@@@@@@@@@@@@@@@@",jObj.getJSONObject("data").toString());
                    SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putString("id", jObj.getJSONObject("data").getString("_id"));
                    editor.putString("username", jObj.getJSONObject("data").getString("username"));
                    editor.putString("name", jObj.getJSONObject("data").getString("name"));
                    editor.putString("email", jObj.getJSONObject("data").getString("email"));
                    editor.putString("profile", jObj.getJSONObject("data").getString("profile"));
                    editor.putString("exp", jObj.getJSONObject("data").getString("exp"));
                    editor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
