package com.example.anzhuo.weibo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anzhuo.weibo.AddActivity;
import com.example.anzhuo.weibo.R;
import com.example.anzhuo.weibo.SetActivity;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class MyFragment extends Fragment{
    private View view;
    TextView tv_add;
    TextView tv_set;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
 view=inflater.inflate(R.layout.me,container,false);
        tv_add= (TextView) view.findViewById(R.id.tv_add);
tv_set= (TextView) view.findViewById(R.id.tv_set);

        tv_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), SetActivity.class);
                startActivity(intent);
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
