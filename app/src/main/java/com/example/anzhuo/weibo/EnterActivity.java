package com.example.anzhuo.weibo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by anzhuo on 2016/8/31.
 */
public class EnterActivity extends AppCompatActivity {
    ImageView iv_cancel;
    EditText et_user;
    EditText et_word;
    Button bt_login;
    ImageView iv_key;
    ImageView iv_user;
    TextView tv_regist;
    String names="";
    String word="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(EnterActivity.this);
        setContentView(R.layout.enter_activity);



DBHeiper heiper=new DBHeiper(this);
        Cursor c=heiper.query(heiper.DB_NAME,null,null,null,null,null,null);
if(c!=null){
    while (c.moveToNext()){
    names=c.getString(c.getColumnIndex("name"));
   word=c.getString(c.getColumnIndex("words"));

}}

        iv_cancel= (ImageView) findViewById(R.id.iv_cancel);
        et_user= (EditText) findViewById(R.id.et_user);
        et_word= (EditText) findViewById(R.id.et_word);
        bt_login= (Button) findViewById(R.id.bt_login);
        iv_key= (ImageView) findViewById(R.id.iv_key);
        iv_user= (ImageView) findViewById(R.id.iv_user);
        tv_regist= (TextView) findViewById(R.id.tv_regist);

        tv_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et_user.getText().toString().equals(names)&&et_word.getText().toString().equals(word)){
                    Toast.makeText(EnterActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EnterActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(EnterActivity.this,"输入错误",Toast.LENGTH_SHORT).show();
                }


            }
        });

        et_word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start>=before) {
                    iv_key.setImageResource(R.drawable.login_key_hightlighted);
                }else {
                    iv_key.setImageResource(R.drawable.login_key);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        et_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start>=before) {
                    iv_user.setImageResource(R.drawable.login_user_hightlighted);
                }else {
                   iv_user.setImageResource(R.drawable.login_user);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

tv_regist.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        Intent intent=new Intent(EnterActivity.this,RegistActivity.class);
        startActivity(intent);
    }
});

    }
}
