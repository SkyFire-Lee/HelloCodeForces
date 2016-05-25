package io.github.skyfire_lee.hellocodeforces;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.logging.LogRecord;

import io.github.skyfire_lee.hellocodeforces.blogAction.InitBlogThread;

/**
 * Created by SkyFire on 2016/5/25.
 */
public class BlogActivity extends AppCompatActivity {

    private String blogEntryId;

    private Handler handler = new Handler();

    private TextView tv_title;
    private TextView tv_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);

        Bundle bundle = getIntent().getExtras();

        blogEntryId = bundle.getCharSequence("blogEntryId").toString();

        (new InitBlogThread(blogEntryId, handler, tv_title, tv_content)).start();
    }
}
