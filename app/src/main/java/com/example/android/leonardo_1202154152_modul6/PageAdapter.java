package com.example.android.leonardo_1202154152_modul6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Leonardo on 4/1/2018.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public PageAdapter(FragmentManager fragmentManager, int NumberOfTabs)
    {
        super(fragmentManager);
        this.mNoOfTabs = NumberOfTabs;
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                FragmentTerbaru tab1 =new FragmentTerbaru();
                return tab1;
            case 1:
                FragmentSendiri tab2 = new FragmentSendiri();
                return tab2;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

}
