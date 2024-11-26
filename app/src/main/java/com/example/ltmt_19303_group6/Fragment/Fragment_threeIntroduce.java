package com.example.ltmt_19303_group6.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ltmt_19303_group6.AdapterView.AdapterViewPager2;
import com.example.ltmt_19303_group6.R;

public class Fragment_threeIntroduce extends Fragment {
    Button btn_next;
    AdapterViewPager2 pager2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.introduce_three, null, false);
        pager2 = new AdapterViewPager2(getActivity());

        return view;
    }
}
