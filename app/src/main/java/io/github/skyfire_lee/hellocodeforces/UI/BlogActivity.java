package io.github.skyfire_lee.hellocodeforces.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.init.InitBlogThread;

/**
 * Created by SkyFire on 2016/5/25.
 */
public class BlogActivity extends AppCompatActivity {

    public String blogEntryId;
    public TextView titleTV;
    public WebView contentWV;
    public Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        findView();

        blogEntryId = (getIntent().getExtras()).getCharSequence("blogEntryId").toString();

        (new InitBlogThread(this)).start();
    }

    private void findView()
    {
        titleTV = (TextView) findViewById(R.id.tv_title);
        contentWV = (WebView) findViewById(R.id.wv_content);
    }
}
