package io.github.skyfire_lee.hellocodeforces.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.init.InitMainThread;


public class MainActivity extends AppCompatActivity {
    public ImageView avatarIV;
    public TextView handleID;
    public TextView ratingTV;
    public LinearLayout ratingLL;
    public LinearLayout contestLL;
    public LinearLayout blogLL;

    public Handler handler = new Handler();
    public String handle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        //进入用户界面按钮点击事件
        avatarIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putCharSequence("handle", handle);
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);       //点击进入详细页面
            }
        });

        ratingLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putCharSequence("handle", handle);
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);       //点击进入详细页面
            }
        });

        contestLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContestActivity.class));
            }
        });

        blogLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActionsActivity.class));
            }
        });
    }

    private void findView()
    {
        avatarIV = (ImageView) findViewById(R.id.iv_avatar);
        handleID = (TextView) findViewById(R.id.tv_handle);
        ratingTV = (TextView) findViewById(R.id.tv_rating);
        ratingLL = (LinearLayout) findViewById(R.id.ll_rating);
        contestLL = (LinearLayout) findViewById(R.id.ll_contest);
        blogLL = (LinearLayout) findViewById(R.id.ll_blog);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //读取数据
        SharedPreferences pref = getSharedPreferences("CodeForcePref", MODE_PRIVATE);
        String _handle = pref.getString("handle", "tourist");

        if(handle == null || _handle.equals(handle) == false)
        {
            (new InitMainThread(this)).start();
        }
    }
}
