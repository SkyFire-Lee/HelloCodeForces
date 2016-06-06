package io.github.skyfire_lee.hellocodeforces.actionsAction;

import android.os.Handler;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.ActionsActivity;
import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.bean.blogBean;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class InitActionsThread extends Thread {

    private ActionsActivity context;

    public InitActionsThread(ActionsActivity context) {
        this.context = context;
    }

    @Override
    public void run() {

        final JSONArray jsonArray;

        try {
            String doc = SuperUtils.getHTML("http://codeforces.com/api/recentActions?maxCount=30");

            jsonArray = new JSONObject(doc).getJSONArray("result");


            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {

                        int len = jsonArray.length();

                        for (int i = 0; i < len ;i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("blogEntry");

                            blogBean BlogBean = new blogBean();

                            BlogBean.setAuthor(jsonObject.getString("authorHandle"));

                            BlogBean.setCreationTimeSeconds(SuperUtils.getDateToString(Long.parseLong(jsonObject.getString("creationTimeSeconds"))));

                            BlogBean.setTitle(jsonObject.getString("title"));

                            BlogBean.setBlogEntryId(jsonObject.getString("id"));

                            context.list.add(BlogBean);
                        }

                        context.actionsAdapter.notifyDataSetChanged();

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
