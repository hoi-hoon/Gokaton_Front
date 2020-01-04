package com.example.myapp;
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
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.Gravity;
import android.text.TextWatcher;
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

}
