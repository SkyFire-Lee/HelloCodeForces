package io.github.skyfire_lee.hellocodeforces;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.bean.contestBean;
import io.github.skyfire_lee.hellocodeforces.contestAction.InitContestThread;
import io.github.skyfire_lee.hellocodeforces.contestAction.contestAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class ContestActivity extends AppCompatActivity{
    private ListView lv_contest;

    private List<contestBean> list = new ArrayList<>();

    private contestAdapter ContestAdapter;

    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        lv_contest = (ListView) findViewById(R.id.lv_contest);

        handler = new Handler();

        ContestAdapter = new contestAdapter(this, list);

        lv_contest.setAdapter(ContestAdapter);

        (new InitContestThread(list, handler, ContestAdapter)).start();

    }
}
