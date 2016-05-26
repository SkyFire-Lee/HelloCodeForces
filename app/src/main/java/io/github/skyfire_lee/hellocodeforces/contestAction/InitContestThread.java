package io.github.skyfire_lee.hellocodeforces.contestAction;

import android.os.Handler;
import android.os.SystemClock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;
import io.github.skyfire_lee.hellocodeforces.bean.rankBean;
import io.github.skyfire_lee.hellocodeforces.ratingAction.rankAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class InitContestThread extends Thread{

    private List<contestBean> list;
    private Handler handler;
    private contestAdapter ContestAdapter;

    public InitContestThread(List<contestBean> list, Handler handler, contestAdapter ContestAdapter) {
        this.list = list;
        this.handler = handler;
        this.ContestAdapter = ContestAdapter;
    }

    @Override
    public void run() {

        final JSONArray jsonArray;

        try {
            String doc = Jsoup.connect("http://codeforces.com/api/contest.list?gym=false").ignoreContentType(true).execute().body();

            jsonArray = new JSONObject(doc).getJSONArray("result");

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        int len = jsonArray.length();

                        for (int i = 0; i < len; i++)
                        {
                            contestBean ContestBean = new contestBean();

                            ContestBean.setName(jsonArray.getJSONObject(i).getString("name")).setDurationSeconds(jsonArray.getJSONObject(i).getString("durationSeconds"));

                            ContestBean.setStartTimeSeconds(SuperUtils.getDateToString(Long.parseLong(jsonArray.getJSONObject(i).getString("startTimeSeconds"))));

                            ContestBean.setPhase(jsonArray.getJSONObject(i).getString("phase"));

                            list.add(ContestBean);
                        }

                        ContestAdapter.notifyDataSetChanged();

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
