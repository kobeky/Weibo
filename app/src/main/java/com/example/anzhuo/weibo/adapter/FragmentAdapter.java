package com.example.anzhuo.weibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class FragmentAdapter extends FragmentPagerAdapter{
    List<Fragment>list;
    public FragmentAdapter(FragmentManager fragmentManager, List<Fragment>list){
        super(fragmentManager);
this.list=list;
    }
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
