package io.github.skyfire_lee.hellocodeforces;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.LogRecord;

import io.github.skyfire_lee.hellocodeforces.blogAction.InitBlogThread;

/**
 * Created by SkyFire on 2016/5/25.
 */
public class BlogActivity extends AppCompatActivity {

    public String blogEntryId;
    public TextView tv_title;
    public WebView wv_content;
    public Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        tv_title = (TextView) findViewById(R.id.tv_title);
        wv_content = (WebView) findViewById(R.id.wv_content);

        Bundle bundle = getIntent().getExtras();

        blogEntryId = bundle.getCharSequence("blogEntryId").toString();

        (new InitBlogThread(this)).start();
    }
}
