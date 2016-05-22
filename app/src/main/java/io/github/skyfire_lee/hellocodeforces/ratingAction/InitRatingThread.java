package io.github.skyfire_lee.hellocodeforces.ratingAction;

import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.bean.rankBean;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class InitRatingThread extends Thread {

    private List<rankBean> list;
    private Handler handler;
    private rankAdapter RankAdapter;
    private String mhandler;

    public InitRatingThread(List<rankBean> list, Handler handler, rankAdapter rankAdapter, String mhandler) {
        this.list = list;
        this.handler = handler;
        RankAdapter = rankAdapter;
        this.mhandler = mhandler;
    }

    @Override
    public void run() {

        final JSONArray jsonArray;

        try {
            String doc = Jsoup.connect("http://codeforces.com/api/user.rating?handle="+mhandler).ignoreContentType(true).execute().body();

            jsonArray = new JSONObject(doc).getJSONArray("result");

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        int len = jsonArray.length();

                        for (int i = len - 1; i >= 0; i--)
                        {
                            list.add(new rankBean((jsonArray.getJSONObject(i).getString("contestName")),(jsonArray.getJSONObject(i).getString("oldRating")),(jsonArray.getJSONObject(i).getString("newRating")),(jsonArray.getJSONObject(i).getString("rank"))));
                        }

                        RankAdapter.notifyDataSetChanged();

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
