package io.github.skyfire_lee.hellocodeforces.blogAction;

import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;

/**
 * Created by SkyFire on 2016/5/25.
 */
public class InitBlogThread extends Thread {

    private String blogEntryId;
    private  Handler handler;
    private TextView tv_title;
    private WebView wv_content;

    public InitBlogThread(String blogEntryId, Handler handler, TextView tv_title, WebView wv_content) {
        this.blogEntryId = blogEntryId;
        this.handler = handler;
        this.tv_title = tv_title;
        this.wv_content = wv_content;
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

                        wv_content.loadDataWithBaseURL("", SuperUtils.getHtmlCss(jsonObject.getString("content")), "text/html",  "utf-8","");

      //                  wv_content.loadData(URLEncoder.encode(jsonObject.getString("content"),"UTF-8"), "text/html",  "utf-8");
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
