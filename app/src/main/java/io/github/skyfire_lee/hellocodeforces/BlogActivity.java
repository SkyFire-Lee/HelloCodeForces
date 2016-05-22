package io.github.skyfire_lee.hellocodeforces;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.bean.blogBean;
import io.github.skyfire_lee.hellocodeforces.blogAction.InitBlogThread;
import io.github.skyfire_lee.hellocodeforces.blogAction.blogAdapter;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class BlogActivity extends AppCompatActivity {
    private ListView lv_blog_list;
    private List<blogBean> list = new ArrayList<>();;
    private blogAdapter BlogAdapter;
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        lv_blog_list  = (ListView) findViewById(R.id.lv_blog_list);
        handler = new Handler();

        BlogAdapter = new blogAdapter(this, list);

        lv_blog_list.setAdapter(BlogAdapter);

        (new InitBlogThread(list, handler, BlogAdapter)).start();
    }
}
