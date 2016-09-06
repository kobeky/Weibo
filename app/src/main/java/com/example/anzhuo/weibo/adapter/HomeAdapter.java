package com.example.anzhuo.weibo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.weibo.Info;
import com.example.anzhuo.weibo.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class HomeAdapter extends BaseAdapter{
    List<Info>list;
    LayoutInflater layoutInflater;
    ViewHolder viewHolder;
    Info info;

    public HomeAdapter(Context context,List<Info>list){
        this.list=list;
        layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
viewHolder=new ViewHolder();
        if(convertView==null||convertView.getTag()==null){
            convertView=layoutInflater.inflate(R.layout.home_item,null);
            viewHolder.iv= (com.facebook.drawee.view.SimpleDraweeView) convertView.findViewById(R.id.iv);
          viewHolder.tv1= (TextView) convertView.findViewById(R.id.tv1);
            viewHolder.tv2= (TextView) convertView.findViewById(R.id.tv2);
            viewHolder.tv3= (TextView) convertView.findViewById(R.id.tv3);
            viewHolder.tv4= (TextView) convertView.findViewById(R.id.tv4);
            viewHolder.tv_comment= (TextView) convertView.findViewById(R.id.tv_comment);
            viewHolder.tv_like= (TextView) convertView.findViewById(R.id.tv_like);
            viewHolder.tv_share= (TextView) convertView.findViewById(R.id.tv_share);
            convertView.setTag(viewHolder);
        }
viewHolder= (ViewHolder) convertView.getTag();
        info=list.get(position);
      viewHolder.iv.setImageURI(Uri.parse(info.getIv()));
        viewHolder.tv1.setText(info.getTv1());
        viewHolder.tv2.setText(info.getTv2());
        viewHolder.tv3.setText(info.getTv3());
        viewHolder.tv4.setText(info.getTv4());
        viewHolder.tv_share.setText(info.getTv_share());
        viewHolder.tv_like.setText(info.getTv_like());
        viewHolder.tv_comment.setText(info.getTv_comment());
        return convertView;
    }
    public class ViewHolder{
        com.facebook.drawee.view.SimpleDraweeView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv_share;
        TextView tv_like;
        TextView tv_comment;

    }
}
