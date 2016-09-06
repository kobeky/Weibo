package com.example.anzhuo.weibo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by anzhuo on 2016/8/30.
 */
public class SendActivity extends AppCompatActivity{
    TextView tv_cancel;
    Button bt_send;
    EditText et_send;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_weibo);
tv_cancel= (TextView) findViewById(R.id.tv_cancel);
        bt_send= (Button) findViewById(R.id.bt_send);
        et_send= (EditText) findViewById(R.id.et_send);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //输入框非空判断
et_send.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(start>=before){
//            Log.i("A",et_send.getText().toString());
            bt_send.setTextColor(Color.WHITE);
            bt_send.setBackgroundColor(Color.CYAN);
            bt_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new Thread(){
                        @Override
                        public void run() {
//        super.run();
                            String url="https://api.weibo.com/2/statuses/update.json";
                            try {
                                URL urls=new URL(url);

                                HttpURLConnection connection= (HttpURLConnection) urls.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setDoOutput(true);
                                String data="status="+ URLEncoder.encode(et_send.getText().toString(),"utf-8")+"&access_token=2.00iCHlwFauUgvD8f5958af3aI3IdWB";
                                OutputStream outputStream=connection.getOutputStream();
                                outputStream.write(data.getBytes());
                                outputStream.flush();
                                outputStream.close();
                                if(connection.getResponseCode()==200){
                                   finish();
                                }

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }
            });
        }else {
            Toast.makeText(SendActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
            bt_send.setClickable(false);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});




    }
}
