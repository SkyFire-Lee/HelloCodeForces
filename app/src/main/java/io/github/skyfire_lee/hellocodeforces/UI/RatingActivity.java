package io.github.skyfire_lee.hellocodeforces.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.rankBean;
import io.github.skyfire_lee.hellocodeforces.init.InitRatingThread;
import io.github.skyfire_lee.hellocodeforces.adapter.rankAdapter;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class RatingActivity extends AppCompatActivity{
    public List<rankBean> list = new ArrayList<>();
    public ListView contestLV;

    public Handler handler = new Handler();
    public String handle;

    public LineChart chartsLC;
    public rankAdapter RankAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        findView();

        handle = (getIntent().getExtras()).getCharSequence("handle").toString();

        (new InitRatingThread(this)).start();
    }

    private void findView()
    {
        contestLV = (ListView) findViewById(R.id.lv_contest);
        chartsLC = (LineChart) findViewById(R.id.lc_charts);

        RankAdapter = new rankAdapter(this, list);
        contestLV.setAdapter(RankAdapter);
    }
}
