package io.github.skyfire_lee.hellocodeforces.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.contestBean;
import io.github.skyfire_lee.hellocodeforces.init.InitContestThread;
import io.github.skyfire_lee.hellocodeforces.adapter.contestAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class ContestActivity extends AppCompatActivity {

    public ListView contestLV;
    public List<contestBean> list = new ArrayList<>();
    public contestAdapter ContestAdapter;
    public Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);

        findView();

        (new InitContestThread(this)).start();
    }

    private void findView() {
        contestLV = (ListView) findViewById(R.id.lv_contest);
        ContestAdapter = new contestAdapter(this, list);
        contestLV.setAdapter(ContestAdapter);
    }
}
