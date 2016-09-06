package com.example.anzhuo.weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;


/**
 * Created by anzhuo on 2016/8/25.
 */
public class NewActivity extends AppCompatActivity{
    ImageButton ib_delete;
    TextView tv_day;
    TextView tv_date;
    TextView tv_write;
    long date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        ib_delete= (ImageButton) findViewById(R.id.ib_delete);
        tv_date= (TextView) findViewById(R.id.tv_date);
        tv_day= (TextView) findViewById(R.id.tv_day);
        tv_write= (TextView) findViewById(R.id.tv_write);

        date=System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd-EEEE");
 String s=simpleDateFormat.format(date);
        String[]strings=s.split("-");
        tv_day.setText(strings[2]);
        tv_date.setText(strings[3]+"\n\n"+strings[1]+"/"+strings[0]);

        ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewActivity.this,SendActivity.class);
                startActivity(intent);
            }
        });
    }
}
