package com.example.priyanshu1.ui.visi_entry;

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

import com.example.priyanshu1.R;

public class VisiEntryFragment extends Fragment {

    private VisiEntryViewModel visiEntryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visiEntryViewModel =
                ViewModelProviders.of(this).get(VisiEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_visitor_entry, container, false);

        return root;
    }
}