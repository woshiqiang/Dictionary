package com.hbck.dictionary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_content;
    private TextView tv_result;
//    http://fanyi.youdao.com/openapi.do?keyfrom=youdao111&key=60638690&type=data&doctype=json&version=1.1&q=%E5%AD%97%E5%85%B8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void onClick(View view) {
        submit();
    }

    private void initView() {
        et_content = (EditText) findViewById(R.id.et_content);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    private void submit() {
        String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "关键词", Toast.LENGTH_SHORT).show();
            return;
        }

        SearchTask task = new SearchTask(handler.obtainMessage(11));
        task.execute(content);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.obj instanceof ResultBean) {
                ResultBean resultBean = (ResultBean) msg.obj;
                tv_result.setText(resultBean.toString());
            } else {
                tv_result.setText("" + msg.obj);
            }

        }
    };
}
