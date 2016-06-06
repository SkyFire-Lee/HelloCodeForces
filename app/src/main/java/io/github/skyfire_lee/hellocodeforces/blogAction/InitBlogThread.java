package io.github.skyfire_lee.hellocodeforces.blogAction;

import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.github.skyfire_lee.hellocodeforces.BlogActivity;
import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;

/**
 * Created by SkyFire on 2016/5/25.
 */
public class InitBlogThread extends Thread {

    private BlogActivity context;

    public InitBlogThread(BlogActivity context) {
        this.context = context;
    }

    @Override
    public void run() {

        final JSONObject jsonObject;

        try {
            String doc = SuperUtils.getHTML("http://www.codeforces.com/api/blogEntry.view?blogEntryId=" + context.blogEntryId);

            jsonObject = new JSONObject(doc).getJSONObject("result");

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        context.tv_title.setText(Html.fromHtml(jsonObject.getString("title")));

                        context.wv_content.loadDataWithBaseURL("", SuperUtils.getHtmlCss(jsonObject.getString("content")), "text/html",  "utf-8","");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.load.setVisibility(View.GONE);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
