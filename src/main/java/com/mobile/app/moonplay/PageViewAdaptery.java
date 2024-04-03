package com.mobile.app.moonplay;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageViewAdaptery  extends FragmentPagerAdapter {
    public PageViewAdaptery(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new homing();
                break;




            case 1:
                fragment=new musichome();
                break;



        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
