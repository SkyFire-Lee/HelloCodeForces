package io.github.skyfire_lee.hellocodeforces.blogAction;

import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

import io.github.skyfire_lee.hellocodeforces.bean.contestBean;

/**
 * Created by SkyFire on 2016/5/25.
 */
public class InitBlogThread extends Thread {

    private String blogEntryId;
    private  Handler handler;
    private TextView tv_title;
    private TextView tv_content;

    public InitBlogThread(String blogEntryId, Handler handler, TextView tv_title, TextView tv_content) {
        this.blogEntryId = blogEntryId;
        this.handler = handler;
        this.tv_content = tv_content;
        this.tv_title = tv_title;
    }

    @Override
    public void run() {

        final JSONObject jsonObject;

        try {
            String doc = Jsoup.connect("http://www.codeforces.com/api/blogEntry.view?blogEntryId=" + blogEntryId).ignoreContentType(true).execute().body();

            jsonObject = new JSONObject(doc).getJSONObject("result");

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        tv_title.setText(Html.fromHtml(jsonObject.getString("title")));

                        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
                        tv_content.setText(Html.fromHtml(jsonObject.getString("content")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
