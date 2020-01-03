package com.example.myapp.ui.postUI;
import android.app.Activity;
import com.example.myapp.ui.home.HomeFragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.myapp.R;
import com.example.myapp.SingUp;

import android.widget.ImageView;
import android.widget.Toast;
import com.example.myapp.MainActivity;
import com.example.myapp.ui.home.HomeFragment;

public class postUIFrangment extends Fragment {
    public static postUIFrangment newInstance() {
        return new postUIFrangment();
    }
    private postUIViewModel postUIViewModel;
    private final int GET_GALLERY_IMGAGE = 200;
    private ImageView imageview;
    private EditText editText;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        postUIViewModel = ViewModelProviders.of(this).get(postUIViewModel.class);
        View root = inflater.inflate(R.layout.fragment_posting, container, false);

        imageview = root.findViewById(R.id.imageView);
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

        editText = root.findViewById(R.id.review_Text);
        textView = root.findViewById(R.id.textLimitView);

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

        textView = root.findViewById(R.id.sendText);
        textView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "수정해야됨", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).replaceFragment(HomeFragment.newInstance());
            }
        });

        return root;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == GET_GALLERY_IMGAGE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }

}