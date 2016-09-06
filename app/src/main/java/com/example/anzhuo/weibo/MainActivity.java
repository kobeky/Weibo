package com.example.anzhuo.weibo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.anzhuo.weibo.adapter.FragmentAdapter;
import com.example.anzhuo.weibo.adapter.HomeAdapter;
import com.example.anzhuo.weibo.fragment.DisFragment;
import com.example.anzhuo.weibo.fragment.HomePage;
import com.example.anzhuo.weibo.fragment.MesFragment;
import com.example.anzhuo.weibo.fragment.MyFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
RadioGroup rg;
    RadioButton rb_sy;
    RadioButton rb_xx;
    RadioButton rb_fx;
    RadioButton rb_w;
    ImageView iv_new;
List<Fragment>list;

DisFragment disFragment;
    MesFragment mesFragment;
    MyFragment myFragment;
    HomePage homePage;
    FragmentAdapter fragmentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg= (RadioGroup) findViewById(R.id.rg);

        rb_w= (RadioButton) findViewById(R.id.rb_w);
        rb_sy= (RadioButton) findViewById(R.id.rb_sy);
        iv_new= (ImageView) findViewById(R.id.iv_new);
        viewPager= (ViewPager) findViewById(R.id.viewPager);

        list=new ArrayList<>();
        homePage=new HomePage();
        mesFragment=new MesFragment();
        disFragment=new DisFragment();
        myFragment=new MyFragment();

        list.add(homePage);
        list.add(mesFragment);
        list.add(disFragment);
        list.add(myFragment);

        fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(fragmentAdapter);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_w:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_sy:
                        viewPager.setCurrentItem(0);

                        break;
                    case R.id.rb_xx:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_fx:
                        viewPager.setCurrentItem(2);
                        break;
                }

            }
        });
        iv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent);
            }
        });
    }

}
