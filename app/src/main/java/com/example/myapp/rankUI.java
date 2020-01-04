package com.example.myapp;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class rankUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.rank_layout);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.rank_searchbtn :
                        Intent intentA = new Intent(getApplicationContext(), searchUI.class);
                        startActivity(intentA);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.rank_homebtn :
                        Intent intentB = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intentB);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                    case R.id.rank_postbtn :
                        Intent intentC = new Intent(getApplicationContext(), postUI.class);
                        startActivity(intentC);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                        break ;
                }
            }
        } ;
        ImageButton btnsearch = (ImageButton) findViewById(R.id.rank_searchbtn) ;
        btnsearch.setOnClickListener(onClickListener) ;
        ImageButton btnhome = (ImageButton) findViewById(R.id.rank_homebtn) ;
        btnhome.setOnClickListener(onClickListener) ;
        ImageButton btnpost = (ImageButton) findViewById(R.id.rank_postbtn) ;
        btnpost.setOnClickListener(onClickListener) ;

        String url = "http://52.79.226.55:8000/api/users/best";

        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
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
            String rank = "rank_";
            try {
                JSONObject jObj = new JSONObject(s);
                JSONArray jsonArray = jObj.getJSONArray("data");
                int[] textViewID = {R.id.rank_1_name,R.id.rank_2_name,R.id.rank_3_name,R.id.rank_4_name,R.id.rank_5_name,
                        R.id.rank_6_name,R.id.rank_7_name,R.id.rank_8_name,R.id.rank_9_name,R.id.rank_10_name,R.id.rank_11_name,
                        R.id.rank_12_name,R.id.rank_13_name,R.id.rank_14_name};
                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject o = jsonArray.getJSONObject(i);
                    TextView iterview = findViewById(textViewID[i]);
                    iterview.setText(o.getString("username"));
                    Log.i("!!!!!!!!!!!!!!!!!!!!!",o.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
