package io.github.skyfire_lee.hellocodeforces.init;

import android.text.Html;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.skyfire_lee.hellocodeforces.ui.BlogActivity;
import io.github.skyfire_lee.hellocodeforces.util.Network;

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
            String doc = Network.getHTML("http://www.codeforces.com/api/blogEntry.view?blogEntryId=" + context.blogEntryId);

            jsonObject = new JSONObject(doc).getJSONObject("result");

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        context.titleTV.setText(Html.fromHtml(jsonObject.getString("title")));

                        context.contentWV.loadDataWithBaseURL("", Network.getHtmlCss(jsonObject.getString("content")), "text/html",  "utf-8","");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
