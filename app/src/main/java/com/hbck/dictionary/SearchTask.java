package com.hbck.dictionary;

import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @Date 2018-11-21.
 */
public class SearchTask extends AsyncTask<String, String, String> {
    public static final String BASE_URL = "http://fanyi.youdao.com/openapi.do?keyfrom=youdao111&key=60638690&type=data&doctype=json&version=1.1&q=";
    private static final String TAG = "SearchTask";
    private Message msg;

    public SearchTask(Message msg) {
        this.msg = msg;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        try {
            //地址 中文转码 为UTF-8格式
//            String keyWord = URLEncoder.encode(strings[0], "UTF-8");
            String keyWord = URLEncoder.encode(strings[0], "utf-8");
            Log.d(TAG, keyWord);
            String strUrl = BASE_URL + keyWord;
            Log.d(TAG, strUrl);
            // 新建一个URL对象
            URL url = new URL(strUrl);

            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
//            urlConn.setConnectTimeout(5 * 1000);
            // 设置为GET请求
            urlConn.setRequestMethod("GET");
            // 开始连接
            urlConn.connect();

            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                result = streamToString(urlConn.getInputStream());
                Log.e(TAG, "Get方式请求成功，result--->" + result);
            } else {
                Log.e(TAG, "Get方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            Gson gson = new Gson();
            ResultBean resultBean = gson.fromJson(s, ResultBean.class);
            msg.obj = resultBean;
        } catch (Exception e) {
            msg.obj = s;
        }
        msg.sendToTarget();
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
}
