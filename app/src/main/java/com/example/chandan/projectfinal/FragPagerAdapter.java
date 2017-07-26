package com.example.chandan.projectfinal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Chandan on 28-04-2017.
 */

public class FragPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> pages=new ArrayList<>();

    public FragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void addPaging(Fragment f){
        pages.add(f);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pages.get(position).toString();
    }
}


