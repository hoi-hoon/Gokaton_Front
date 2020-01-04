package com.example.myapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginUI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Button btn = findViewById(R.id.login_button);

        final EditText et_id = findViewById(R.id.login_id);
        final EditText et_pw = findViewById(R.id.login_pw);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://52.79.226.55:8000/api/auth/login";
                String user_id = et_id.getText().toString();
                String user_pw = et_pw.getText().toString();

                ContentValues signup_info = new ContentValues();
                signup_info.put("username",user_id);
                signup_info.put("password",user_pw);

                // AsyncTask를 통해 HttpURLConnection 수행.
                NetworkTask networkTask = new NetworkTask(url, signup_info);
                networkTask.execute();
            }
        });
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
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String token) {
            super.onPostExecute(token);
            try {
                JSONObject jObj = new JSONObject(token);
                token = jObj.get("data").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("http response",token);
            Toast.makeText(loginUI.this, token, Toast.LENGTH_SHORT).show();
            //토큰 저장 코드, 값을 저장한다.
            SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("TokenCode", token);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}