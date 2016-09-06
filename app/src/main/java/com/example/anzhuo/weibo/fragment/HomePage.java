package com.example.anzhuo.weibo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anzhuo.weibo.FriendfollowActivity;
import com.example.anzhuo.weibo.Info;
import com.example.anzhuo.weibo.MainActivity;
import com.example.anzhuo.weibo.R;
import com.example.anzhuo.weibo.RefreshListView;
import com.example.anzhuo.weibo.adapter.HomeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by anzhuo on 2016/8/24.
 */
public class HomePage extends Fragment {
    RefreshListView refreshListView;
in.srain.cube.views.ptr.PtrFrameLayout frameLayout;


private View view;
    TextView tv_friend;
    TextView tv_jz;

    Info info;
    List<Info>list=new ArrayList<>();
    HomeAdapter homeAdapter;
    StringBuffer stringBuffer;
   ListView listView;

    PopupWindow popupWindow;
    String url="";
    Bitmap bitmap;
    private static final int MSG=1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG:
                    try {
                        JSONObject jsonObject=new JSONObject(stringBuffer.toString());
                        JSONArray jsonArray=jsonObject.optJSONArray("statuses");
                        for(int i=0;i<jsonArray.length();i++){
                            info=new Info();
                            JSONObject jsonObject1= (JSONObject) jsonArray.get(i);

                            String time=jsonObject1.getString("created_at");
                            String []strings=time.split(" ");
                            String[]strings1=strings[3].split(":");
int m=Integer.parseInt(strings1[1]);
                            long date=System.currentTimeMillis();
                            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh-mm-ss");
                            String s=simpleDateFormat.format(date);
                            String []strings2=s.split("-");
                            int n=Integer.parseInt(strings2[1]);
                            int mn=n-m;
                            String times=String.valueOf(mn);

                            String text=jsonObject1.getString("text");
                            int share=jsonObject1.getInt("reposts_count");
                            String shares=String.valueOf(share);
                            int comment=jsonObject1.getInt("comments_count");
                            String comments=String.valueOf(comment);
                            int like=jsonObject1.getInt("attitudes_count");
                            String likes=String.valueOf(like);
                            String from=jsonObject1.getString("source");
                            if(from.split(">").length>1){
                            String froms=from.substring(from.indexOf(">")+1,from.lastIndexOf("<"));
                                info.setTv2(times+"分钟前  "+"来自"+froms);
                            }else {
                                info.setTv2(times+"分钟前  "+"来自未知设备");
                            }
                            JSONObject jsonObject2=jsonObject1.getJSONObject("user");

                        url=jsonObject2.getString("profile_image_url");


                            String name=jsonObject2.getString("name");
                            String loc=jsonObject2.getString("location");
                            info.setIv(url);
                            info.setTv1(name);

                            info.setTv3(text);
                            info.setTv4(loc);
                            info.setTv_comment(comments);
                            info.setTv_like(likes);
                            info.setTv_share(shares);
                            list.add(0,info);
                        }
                        homeAdapter=new HomeAdapter(getActivity().getApplicationContext(),list);
                        listView.setAdapter(homeAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.home_page,container,false);
         listView= (ListView) view.findViewById(R.id.listView);
        frameLayout= (PtrFrameLayout) view.findViewById(R.id.store_house_ptr_frame);
        refreshListView=new RefreshListView(getContext());
        setThread();
        frameLayout.setHeaderView(refreshListView);
        frameLayout.addPtrUIHandler(refreshListView);
        frameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
            frameLayout.postDelayed(new Runnable() {
    @Override
    public void run() {
        frameLayout.refreshComplete();
      setThread();
    }
},2000);

            }
        });




        tv_jz= (TextView) view.findViewById(R.id.tv_jz);
        tv_friend= (TextView) view.findViewById(R.id.tv_friend);

        tv_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), FriendfollowActivity.class);
                startActivity(intent);
            }
        });



        popupWindow=new PopupWindow();
      View  views=LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.pop_item1,null);
        popupWindow.setContentView(views);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        //BackgroundDrawable必须设置，否则会出bug
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        tv_jz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
popupWindow.showAsDropDown(tv_jz);
            }
        });


        return view;
    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        listView= (RefreshListView) view.findViewById(R.id.lv);
//        listView.setRefreshListener((RefreshListView.onRefreshListener) this);
//    }

    private void netWork(String url) {
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            stringBuffer = new StringBuffer();
            byte[] bytes = new byte[5 * 1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                stringBuffer.append(new String(bytes, 0, len));
            }
            handler.sendEmptyMessage(MSG);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setThread(){
        new Thread() {
            @Override
            public void run() {
                String  url = "https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00iCHlwFauUgvD8f5958af3aI3IdWB";
                netWork(url);
            }
        }.start();
    }



}
