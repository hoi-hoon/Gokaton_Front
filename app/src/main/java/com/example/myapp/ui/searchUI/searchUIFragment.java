package com.example.myapp.ui.searchUI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapp.R;

public class searchUIFragment extends Fragment {

    private searchUIViewModel searchUIViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchUIViewModel =
                ViewModelProviders.of(this).get(searchUIViewModel.class);
        View root = inflater.inflate(R.layout.frament_search, container, false);
        final TextView textView = root.findViewById(R.id.text_search);
        searchUIViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}