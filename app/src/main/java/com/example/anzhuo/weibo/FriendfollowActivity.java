package com.example.anzhuo.weibo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by anzhuo on 2016/8/29.
 */
public class FriendfollowActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_back;
    TextView tv_more;
    ImageView iv_follow1;
    ImageView iv_follow2;
    ImageView iv_follow3;
    PopupWindow popupWindow;


    String[]strings={"搞笑幽默","特别关注","美女","同事","同学"};
    Boolean[]checked={false,false,false,false,false};

    AlertDialog.Builder a;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendfollow);
        tv_back= (TextView) findViewById(R.id.tv_back);
        tv_more= (TextView) findViewById(R.id.tv_more);
        iv_follow1= (ImageView) findViewById(R.id.iv_follow1);
        iv_follow2= (ImageView) findViewById(R.id.iv_follow2);
        iv_follow3= (ImageView) findViewById(R.id.iv_follow3);

        tv_back.setOnClickListener(this);
        tv_more.setOnClickListener(this);
        iv_follow3.setOnClickListener(this);
        iv_follow2.setOnClickListener(this);
        iv_follow1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        popupWindow=new PopupWindow();
        View view= LayoutInflater.from(FriendfollowActivity.this).inflate(R.layout.pop_item3,null);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);



        switch (v.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_more:
                popupWindow.showAtLocation(tv_more, Gravity.BOTTOM,0,0);
                break;
            case R.id.iv_follow1:

                break;

                }
    }
}
