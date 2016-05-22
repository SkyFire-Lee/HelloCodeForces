package io.github.skyfire_lee.hellocodeforces.blogAction;

import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.bean.blogBean;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;
import io.github.skyfire_lee.hellocodeforces.contestAction.contestAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class InitBlogThread extends Thread {
    private List<blogBean> list;
    private Handler handler;
    private blogAdapter BlogAdapter;

    public InitBlogThread(List<blogBean> list, Handler handler, blogAdapter blogAdapter) {
        this.list = list;
        this.handler = handler;
        BlogAdapter = blogAdapter;
    }

    @Override
    public void run() {

        final JSONArray jsonArray;

        try {
            String doc = Jsoup.connect("http://codeforces.com/api/recentActions?maxCount=30").ignoreContentType(true).execute().body();

            jsonArray = new JSONObject(doc).getJSONArray("result");


            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {

                        int len = jsonArray.length();

                        for (int i = 0; i < len ;i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("blogEntry");
                            System.out.println(jsonObject.getString("creationTimeSeconds"));

                            blogBean BlogBean = new blogBean();

                            BlogBean.setAuthor(jsonObject.getString("authorHandle"));

                            BlogBean.setCreationTimeSeconds(jsonObject.getString("creationTimeSeconds"));

                            BlogBean.setTitle(jsonObject.getString("title"));

                            list.add(BlogBean);
                        }

                        BlogAdapter.notifyDataSetChanged();

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
