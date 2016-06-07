package io.github.skyfire_lee.hellocodeforces.init;

import android.graphics.Color;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.github.skyfire_lee.hellocodeforces.ui.RatingActivity;
import io.github.skyfire_lee.hellocodeforces.util.Network;
import io.github.skyfire_lee.hellocodeforces.bean.rankBean;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class InitRatingThread extends Thread {

    private RatingActivity context;

    public InitRatingThread(RatingActivity context)
    {
        this.context = context;
    }

    @Override
    public void run() {

        final JSONArray jsonArray;

        try {
            String doc = Network.getHTML("http://codeforces.com/api/user.rating?handle="+ context.handle);

            jsonArray = new JSONObject(doc).getJSONArray("result");

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        int len = jsonArray.length();

                        for (int i = len - 1; i >= 0; i--)
                        {
                            context.list.add(new rankBean((jsonArray.getJSONObject(i).getString("contestName")),(jsonArray.getJSONObject(i).getString("oldRating")), (jsonArray.getJSONObject(i).getString("newRating")),(jsonArray.getJSONObject(i).getString("rank"))));
                        }

                        context.RankAdapter.notifyDataSetChanged();

                        context.chartsLC.setData(getData(context.list.size()));

                        context.chartsLC.animateX(2500);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private LineData getData(int count) {
        ArrayList<String> xVals = new ArrayList<String>();


        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0, j = count - 1; i < count; i++, j--) {
            xVals.add(context.list.get(j).getContestName());
            yVals.add(new Entry((float)(Integer.parseInt(context.list.get(j).getNewRating())), i));
        }

        LineDataSet set1 = new LineDataSet(yVals, "Source");


        set1.setLineWidth(1.75f); // 线宽
        set1.setColor(Color.BLACK);// 显示颜色
        set1.setCircleColor(Color.BLACK);// 圆形的颜色
        set1.setHighLightColor(Color.BLACK); // 高亮的线的颜色

        // create a data object with the datasets
        LineData data = new LineData(xVals, set1);

        return data;
    }
}
