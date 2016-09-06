package com.example.anzhuo.weibo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anzhuo.weibo.Bean;
import com.example.anzhuo.weibo.Info;
import com.example.anzhuo.weibo.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anzhuo on 2016/8/26.
 */
public class DisFragment extends Fragment{
    private View view;
    ImageView iv;
    OkHttpClient okHttpClient;

    String string="";

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Gson gson=new Gson();
                   Bean bean=gson.fromJson(string,Bean.class);

                    Picasso.with(getActivity()).load(bean.getStatuses().get(0).getUser().getProfile_image_url()).into(iv);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dis_fragment,container,false);
        iv= (ImageView) view.findViewById(R.id.iv);
okHttpClient=new OkHttpClient();
        new Thread(){
            @Override
            public void run() {
                try {
                    getUrl("https://api.weibo.com/2/statuses/public_timeline.json?access_token=2.00iCHlwFauUgvD8f5958af3aI3IdWB");
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        return view;
    }

    private String getUrl(String s) throws IOException {
        Request request=new Request.Builder().url(s).build();
        Response response=okHttpClient.newCall(request).execute();
        string=response.body().string();
        return string;
    }
}
