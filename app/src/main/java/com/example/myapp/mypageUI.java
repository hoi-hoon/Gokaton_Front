package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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

        //

        String url = "http://52.79.226.55:8000/api/auth/me";

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();

        //
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

            Log.i("갈꺼야",token);
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values,"GET",token);

            try {
                JSONObject jObj = new JSONObject(result);
                Log.i("받았엉",jObj.toString());
                String level = jObj.getJSONObject("data").getString("level");
                String name = jObj.getJSONObject("data").getString("name");
                TextView textView = (TextView)findViewById(R.id.mypage_title);
                textView.setText("이름 : " +name+" 솔플 레벨 : "+level);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s!=null) {
                User user = new User();
                try {
                    JSONObject jObj = new JSONObject(s);
                    Log.i("나 갔따왔어",jObj.getJSONObject("data").toString());
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
