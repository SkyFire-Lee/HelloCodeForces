package io.github.skyfire_lee.hellocodeforces.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.blogBean;
import io.github.skyfire_lee.hellocodeforces.init.InitActionsThread;
import io.github.skyfire_lee.hellocodeforces.adapter.actionsAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class ActionsActivity extends AppCompatActivity {

    public ListView bloglistLV;
    public List<blogBean> list = new ArrayList<>();
    ;
    public actionsAdapter actionsAdapter;
    public Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        findView();

        (new InitActionsThread(this)).start();

        bloglistLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();

                bundle.putCharSequence("blogEntryId", list.get(position).getBlogEntryId());

                Intent intent = new Intent(ActionsActivity.this, BlogActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);       //点击进入详细页面
            }
        });
    }

    private void findView()
    {
        bloglistLV = (ListView) findViewById(R.id.lv_blog_list);

        actionsAdapter = new actionsAdapter(this, list);

        bloglistLV.setAdapter(actionsAdapter);
    }
}
