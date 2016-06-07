package io.github.skyfire_lee.hellocodeforces.init;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.skyfire_lee.hellocodeforces.ui.ActionsActivity;
import io.github.skyfire_lee.hellocodeforces.util.Network;
import io.github.skyfire_lee.hellocodeforces.bean.blogBean;
import io.github.skyfire_lee.hellocodeforces.util.Time;

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
            String doc = Network.getHTML("http://codeforces.com/api/recentActions?maxCount=30");

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

                            BlogBean.setCreationTimeSeconds(Time.getDateToString(Long.parseLong(jsonObject.getString("creationTimeSeconds"))));

                            BlogBean.setTitle(jsonObject.getString("title"));

                            BlogBean.setBlogEntryId(jsonObject.getString("id"));

                            context.list.add(BlogBean);
                        }

                        context.actionsAdapter.notifyDataSetChanged();

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
