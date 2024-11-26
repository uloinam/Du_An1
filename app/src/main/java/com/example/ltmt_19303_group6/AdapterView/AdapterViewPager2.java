package com.example.ltmt_19303_group6.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ltmt_19303_group6.Fragment.Fragment_TwoIntroduce;
import com.example.ltmt_19303_group6.Fragment.Fragment_oneIntroduce;
import com.example.ltmt_19303_group6.Fragment.Fragment_threeIntroduce;

public class AdapterViewPager2 extends FragmentStateAdapter {
    public AdapterViewPager2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fragment_oneIntroduce();
            case 1:
                return new Fragment_TwoIntroduce();
            case 2:
                return new Fragment_threeIntroduce();
            default:
                return new Fragment_oneIntroduce();
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
