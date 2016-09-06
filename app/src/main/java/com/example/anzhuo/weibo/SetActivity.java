package com.example.anzhuo.weibo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by anzhuo on 2016/8/30.
 */
public class SetActivity extends AppCompatActivity{
TextView tv_back;
    TextView tv_quit;
    TextView tv_manage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_activity);
tv_back= (TextView) findViewById(R.id.tv_back);
        tv_quit= (TextView) findViewById(R.id.tv_quit);
        tv_manage= (TextView) findViewById(R.id.tv_manage);


        tv_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,EnterActivity.class);
                startActivity(intent);
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder alertDialog=new AlertDialog.Builder(SetActivity.this);
                alertDialog.setTitle("确认退出微博");

                alertDialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent=new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        System.exit(0);
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SetActivity.this,"确认取消",Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        });
    }
}
