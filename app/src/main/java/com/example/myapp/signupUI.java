package com.example.myapp;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class signupUI extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Button btn = findViewById(R.id.signup_btn);

        final EditText et_id = findViewById(R.id.user_name);
        final EditText et_name = findViewById(R.id.name);
        final EditText et_email = findViewById(R.id.email);
        final EditText et_pw = findViewById(R.id.password);
        final EditText et_pw_chk = findViewById(R.id.password_confirm);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://52.79.226.55:8000/api/users";
                String user_id = et_id.getText().toString();
                String user_name = et_name.getText().toString();
                String user_email = et_email.getText().toString();
                String user_pw = et_pw.getText().toString();
                String user_pw_chk = et_pw_chk.getText().toString();

                ContentValues signup_info = new ContentValues();
                signup_info.put("password",user_pw);
                signup_info.put("passwordConfirmation",user_pw_chk);
                signup_info.put("username",user_id);
                signup_info.put("name",user_name);
                signup_info.put("email",user_email);

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
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("http response",s);
            finish();
        }
    }
}
