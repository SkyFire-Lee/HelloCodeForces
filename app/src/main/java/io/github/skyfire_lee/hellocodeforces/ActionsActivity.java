package io.github.skyfire_lee.hellocodeforces;

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

import io.github.skyfire_lee.hellocodeforces.bean.blogBean;
import io.github.skyfire_lee.hellocodeforces.actionsAction.InitActionsThread;
import io.github.skyfire_lee.hellocodeforces.actionsAction.actionsAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class ActionsActivity extends AppCompatActivity {
    private ListView lv_blog_list;
    private List<blogBean> list = new ArrayList<>();;
    private actionsAdapter actionsAdapter;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        lv_blog_list  = (ListView) findViewById(R.id.lv_blog_list);
        handler = new Handler();

        actionsAdapter = new actionsAdapter(this, list);

        lv_blog_list.setAdapter(actionsAdapter);

        (new InitActionsThread(list, handler, actionsAdapter)).start();

        lv_blog_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
}
