package com.example.anzhuo.weibo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupWindowCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.anzhuo.weibo.AddGroup;
import com.example.anzhuo.weibo.R;

/**
 * Created by anzhuo on 2016/8/25.
 */
public class MesFragment extends Fragment{
    View view;

    TextView tv_chat;
    TextView tv_group;
    PopupWindow popupWindow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.mes_fragment,container,false);
tv_chat= (TextView) view.findViewById(R.id.tv_chat);
        tv_group= (TextView) view.findViewById(R.id.tv_group);

        popupWindow=new PopupWindow();
        View view1=LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.pop_item2,null);
        popupWindow.setContentView(view1);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
tv_chat.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        popupWindow.showAsDropDown(tv_chat);
    }
});
        tv_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), AddGroup.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
