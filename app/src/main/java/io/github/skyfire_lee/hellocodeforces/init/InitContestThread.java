package io.github.skyfire_lee.hellocodeforces.init;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.github.skyfire_lee.hellocodeforces.ui.ContestActivity;
import io.github.skyfire_lee.hellocodeforces.util.Network;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;
import io.github.skyfire_lee.hellocodeforces.util.Time;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class InitContestThread extends Thread{

    private ContestActivity context;

    public InitContestThread(ContestActivity context) {
        this.context = context;
    }

    @Override
    public void run() {

        final JSONArray jsonArray;

        try {
            String doc = Network.getHTML("http://codeforces.com/api/contest.list?gym=false");

            jsonArray = new JSONObject(doc).getJSONArray("result");

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        int len = jsonArray.length();

                        for (int i = 0; i < Math.min(len, 30); i++)
                        {
                            contestBean ContestBean = new contestBean();

                            ContestBean.setName(jsonArray.getJSONObject(i).getString("name")).setDurationSeconds(jsonArray.getJSONObject(i).getString("durationSeconds"));

                            ContestBean.setStartTimeSeconds(Time.getDateToString(Long.parseLong(jsonArray.getJSONObject(i).getString("startTimeSeconds"))));

                            ContestBean.setPhase(jsonArray.getJSONObject(i).getString("phase"));

                            context.list.add(ContestBean);
                        }

                        context.ContestAdapter.notifyDataSetChanged();


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
