package com.mobile.app.moonplay;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageViewAdapter  extends FragmentPagerAdapter {
    public PageViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new homecurat();

                break;

            case 1:
                fragment=new xxxx();

                break;

            case 2:
                fragment=new offthewall2();

                break;

            case 3:
                fragment=new makemusic();

                break;



        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
