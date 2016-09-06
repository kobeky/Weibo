package com.example.anzhuo.weibo;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by anzhuo on 2016/8/31.
 */
public class RegistActivity extends AppCompatActivity{
    TextView tv_cancel;
    EditText et_name;
    EditText et_word;
    EditText et_words;
    Button bt_regist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);

        tv_cancel= (TextView) findViewById(R.id.tv_cancel);
        et_name= (EditText) findViewById(R.id.et_name);
        et_word= (EditText) findViewById(R.id.et_word);
        et_words= (EditText) findViewById(R.id.et_words);
        bt_regist= (Button) findViewById(R.id.bt_regist);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_words.getText().toString().equals(et_word.getText().toString())) {
                    String name = et_name.getText().toString();
                    String words =et_words.getText().toString();
                    ContentValues values=new ContentValues();
                    values.put("name",name);
                    values.put("words",words);
                    DBHeiper heiper=new DBHeiper(getApplicationContext());
                    heiper.insert(values);

                    Intent intent=new Intent(RegistActivity.this,EnterActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(RegistActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
