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

import android.text.TextWatcher;
public class postUI extends AppCompatActivity {
    private final int GET_GALLERY_IMGAGE = 200;
    private ImageView imageview;
    private EditText editText;
    private TextView textView;
    private TextView textViewB;
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
                Toast.makeText(getApplicationContext(), "수정해야됨", Toast.LENGTH_SHORT).show();
                
            }
        });
    }

}
