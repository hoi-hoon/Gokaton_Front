package com.example.myapp;
import android.content.ContentValues;
import android.database.Cursor;
import java.io.FileInputStream;
import java.io.File;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.provider.MediaStore;
import android.widget.EditText;
import android.text.Editable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.Gravity;
import android.text.TextWatcher;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.util.ArrayList;
public class postUI extends AppCompatActivity {
    private final int GET_GALLERY_IMGAGE = 200;
    private ImageView imageview;
    private EditText editText;
    private TextView textView;
    private TextView textViewB;
    private TextView tagtext;
    private int count_Tag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.post_layout);

        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent, GET_GALLERY_IMGAGE);
            }
        });

        editText = (EditText)findViewById(R.id.review_Text);
        textView = (TextView)findViewById(R.id.textLimitView);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = editText.getText().toString();
                textView.setText(input.length()+"/200");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textViewB = (TextView)findViewById(R.id.sendText);
        textViewB.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {

                SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
                String uuid = sp.getString("id", "");
                String token = sp.getString("TokenCode", "");
                Log.i("시발시발",token);
                List<String> tagss = new ArrayList<>();
                String url = "http://52.79.226.55:8000/api/reviews/";
                final EditText rev_text = findViewById(R.id.review_Text);
                //a,c,d,b,f,d
                TextView textView = (TextView)findViewById(R.id.tagA);
                tagss.add(textView.getText().toString());
                textView = (TextView)findViewById(R.id.tagC);
                tagss.add(textView.getText().toString());
                textView = (TextView)findViewById(R.id.tagD);
                tagss.add(textView.getText().toString());
                textView = (TextView)findViewById(R.id.tagB);
                tagss.add(textView.getText().toString());
                textView = (TextView)findViewById(R.id.tagF);
                tagss.add(textView.getText().toString());
                textView = (TextView)findViewById(R.id.tagE);
                tagss.add(textView.getText().toString());
                String rvtext = rev_text.getText().toString();

                ContentValues post_info = new ContentValues();

                post_info.put("userId",uuid);
                Gson gson = new Gson();
                String jsonPlace = gson.toJson(tagss);
                post_info.put("tags",jsonPlace);
                post_info.put("review",rvtext);
                Log.i("되라고제발",post_info.toString());

                NetworkTask networkTask = new NetworkTask(url, post_info);
                networkTask.execute();

                // 작성기능
                Intent intent = new Intent();
                intent.putExtra("result", "포스팅 완료!");
                setResult(RESULT_OK, intent);
                finish();
                Intent intentA = new Intent(getApplicationContext(), reviewUI.class);
                startActivity(intentA);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        tagtext = (TextView)findViewById(R.id.tag_text);
        ImageButton imagebtn = (ImageButton)findViewById(R.id.post_tagbtn);
        imagebtn.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v){
                switch(count_Tag){

                    case 0:
                        TextView taga = (TextView)findViewById(R.id.tagA);
                        taga.setText("#"+tagtext.getText().toString());
                        count_Tag = count_Tag+1;
                        break;
                    case 1:
                        TextView tagb = (TextView)findViewById(R.id.tagC);
                        tagb.setText("#"+tagtext.getText().toString());
                        count_Tag = count_Tag+1;
                        break;
                    case 2:
                        TextView tagc = (TextView)findViewById(R.id.tagD);
                        tagc.setText("#"+tagtext.getText().toString());
                        count_Tag = count_Tag+1;
                        break;
                    case 3:
                        TextView tagd = (TextView)findViewById(R.id.tagB);
                        tagd.setText("#"+tagtext.getText().toString());
                        count_Tag = count_Tag+1;
                        break;
                    case 4:
                        TextView tage = (TextView)findViewById(R.id.tagF);
                        tage.setText("#"+tagtext.getText().toString());
                        count_Tag = count_Tag+1;
                        break;
                    case 5:
                        TextView tagf = (TextView)findViewById(R.id.tagE);
                        tagf.setText("#"+tagtext.getText().toString());
                        count_Tag = count_Tag+1;
                        break;
                    case 6:
                        Toast toast = Toast.makeText(getApplicationContext(), "그만 넣어", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                        break;
                }
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
            SharedPreferences sp = getSharedPreferences("UserTokenKey", MODE_PRIVATE);
            String token = sp.getString("TokenCode", "");

            Log.i("갈꺼야",token);
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values,"POST",token);

            try {
                JSONObject jObj = new JSONObject(result);
                Log.i("받았엉",jObj.toString());

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

                    editor.putString("id", jObj.getJSONObject("data").getString("id"));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_GALLERY_IMGAGE){
            //sendPicture(data.getData());
        }
    }
    /*
    private void sendPicture(Uri imgUri) {
        Log.e("이미지이미지이미지이미지", imgUri.toString());
        String imagePath = getRealPathFromURI(imgUri);
        Log.e("이미지이미지이미지이미지", imagePath);

        File file = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(file);
        ImageIO.write(bufferedImage, "png", bufferedImage);

        bufferedImage.flush();
        byte[] imageByte = bufferedImage.toByteArray();
        bufferedImage.close();
        return imageByte;
    }

     */


    private String getRealPathFromURI(Uri contentUri) {

        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        cursor.moveToFirst();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        return cursor.getString(column_index);
    }
}
