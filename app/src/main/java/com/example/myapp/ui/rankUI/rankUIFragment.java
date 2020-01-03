package com.example.myapp.ui.rankUI;

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

public class rankUIFragment extends Fragment {

    private rankUIViewModel rankUIViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rankUIViewModel =
                ViewModelProviders.of(this).get(rankUIViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rank, container, false);

        return root;
    }
}