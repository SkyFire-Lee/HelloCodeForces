package io.github.skyfire_lee.hellocodeforces;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.bean.rankBean;
import io.github.skyfire_lee.hellocodeforces.ratingAction.InitRatingThread;
import io.github.skyfire_lee.hellocodeforces.ratingAction.rankAdapter;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class RatingActivity extends AppCompatActivity{
    private WebView wv_echarts;
    private ListView lv_contest;
    private Handler handler;
    private String mhandler;
    private List<rankBean> list = new ArrayList<>();

    private rankAdapter RankAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        wv_echarts = (WebView) findViewById(R.id.wv_echarts);
        lv_contest = (ListView) findViewById(R.id.lv_contest);

        RankAdapter = new rankAdapter(this, list);
        handler = new Handler();

        lv_contest.setAdapter(RankAdapter);

        Bundle bundle = getIntent().getExtras();
        mhandler = bundle.getCharSequence("handler").toString();

    }

    @Override
    protected void onStart() {
        super.onStart();

        (new InitRatingThread(list, handler, RankAdapter, mhandler)).start();
    }
}
