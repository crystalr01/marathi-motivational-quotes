package com.rameshwar.motivationalquotesenglish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new LiveFragment();
        } else if (position == 1) {
            return new QuotesFragment();
        } else if (position == 2){
            return new TrendingFragment();
        } else if (position == 3) {
            return new ShayariFragment();
        } else {
            return new FestivalFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "Live";
        } else if (position == 1) {
            return "Quotes";
        } else if (position == 2){
            return "Status";
        } else if (position == 3) {
            return "Shayari & SMS";
        } else {
            return "Festival";
        }
    }
}