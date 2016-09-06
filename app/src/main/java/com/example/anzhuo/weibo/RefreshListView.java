package com.example.anzhuo.weibo;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by anzhuo on 2016/8/31.
 */
 public class RefreshListView extends FrameLayout implements PtrUIHandler {
ImageView arrow;
    TextView tip;

    public RefreshListView(Context context) {
        this(context,null);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.headerview,this);
        arrow= (ImageView) view.findViewById(R.id.arrow);
        tip= (TextView) view.findViewById(R.id.tip);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        tip.setText("下拉刷新");
        arrow.setVisibility(VISIBLE);
        arrow.setImageResource(R.drawable.indicator_arrow);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        tip.setText("下拉");
        arrow.setVisibility(VISIBLE);
        arrow.setImageResource(R.drawable.indicator_arrow);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        tip.setText("正在刷新");

arrow.setVisibility(VISIBLE);
        arrow.setImageResource(R.drawable.common_loading);
        RotateAnimation rotateAnimation=new RotateAnimation(0,720, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        arrow.startAnimation(rotateAnimation);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        tip.setText("完成刷新");
arrow.setVisibility(INVISIBLE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//设置的刷新高度
        int moff=frame.getOffsetToRefresh();
        //当前下拉高度
        int currpos=ptrIndicator.getCurrentPosY();
//下拉箭头方向改变时候的高度
        int lastPos=ptrIndicator.getLastPosY();
        if(currpos<moff&&lastPos>=moff){
if(isUnderTouch&&status==PtrFrameLayout.PTR_STATUS_PREPARE){
    tip.setText("下拉刷新");
    arrow.setVisibility(VISIBLE);
    arrow.setImageResource(R.drawable.indicator_arrow);
}
        }else if(currpos>moff&&lastPos<=moff){
            tip.setText("释放刷新");
            arrow.setVisibility(VISIBLE);
            arrow.setImageResource(R.drawable.z_arrow_up);
        }

    }
}
