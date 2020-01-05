package com.example.myapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginUI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
        String token = sp.getString("TokenCode", "");
        if(token.length() > 12){
            Log.i("@@@@@@@@@@@@@@@token",token);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Log.i("here","0");
            startActivityForResult(intent,1);
        }
        super.onCreate(savedInstanceState);
        //상태바 안 보이게
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        ImageButton sign_btn = findViewById(R.id.google_btn);
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sign = new Intent(getApplicationContext(), signupUI.class);
                Log.i("here","0");
                startActivityForResult(intent_sign,1);
                    }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("here","1");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Log.i("here","2");
            if (resultCode == RESULT_OK) {
                Log.i("here","3");
                Toast.makeText(loginUI.this, "Result: " + data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            } else if(resultCode == 3) {
                Log.i("here","ggggooooodddd");
            }
                else
             {   // RESULT_CANCEL
                Log.i("here","10");
                Toast.makeText(loginUI.this, "Failed", Toast.LENGTH_SHORT).show();
                finish();
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
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values,"POST",null); // 해당 URL로부터 결과물을 얻어온다.
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
            if(token=="null"){
                Toast.makeText(loginUI.this, "잘못 입력했습니다", Toast.LENGTH_SHORT).show();
            }
            else {
                // Log.i("http response", token);
                // Toast.makeText(loginUI.this, token, Toast.LENGTH_SHORT).show();
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
}
