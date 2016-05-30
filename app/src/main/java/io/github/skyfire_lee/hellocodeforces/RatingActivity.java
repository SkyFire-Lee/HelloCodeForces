package io.github.skyfire_lee.hellocodeforces;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.bean.rankBean;
import io.github.skyfire_lee.hellocodeforces.ratingAction.InitRatingThread;
import io.github.skyfire_lee.hellocodeforces.ratingAction.rankAdapter;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class RatingActivity extends AppCompatActivity{
    public ListView lv_contest;
    public Handler handler;
    public String nickname;
    public LineChart lc_charts;
    public List<rankBean> list = new ArrayList<>();

    public rankAdapter RankAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        lv_contest = (ListView) findViewById(R.id.lv_contest);
        lc_charts = (LineChart) findViewById(R.id.lc_charts);

        RankAdapter = new rankAdapter(this, list);
        lv_contest.setAdapter(RankAdapter);
        handler = new Handler();

        Bundle bundle = getIntent().getExtras();
        nickname = bundle.getCharSequence("handler").toString();

        (new InitRatingThread(this)).start();
    }
}
