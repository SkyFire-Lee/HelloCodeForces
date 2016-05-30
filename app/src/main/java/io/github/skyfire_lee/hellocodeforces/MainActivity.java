package io.github.skyfire_lee.hellocodeforces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.skyfire_lee.hellocodeforces.mainAction.InitMainThread;


public class MainActivity extends AppCompatActivity {

    public ImageView id_avatar;
    public TextView id_name;
    public TextView id_rating;
    public TextView tv_rating;
    public TextView tv_s_contest;
    public TextView tv_s_blog;

    public Handler handler;
    public String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定界面控件
        handler = new Handler();
        id_avatar = (ImageView) findViewById(R.id.id_avatar);
        id_name = (TextView) findViewById(R.id.id_name);
        id_rating = (TextView) findViewById(R.id.id_rating);
        tv_rating = (TextView) findViewById(R.id.tv_s_rating);
        tv_s_contest = (TextView) findViewById(R.id.tv_s_contest);
        tv_s_blog = (TextView) findViewById(R.id.tv_s_blog);


        //进入用户界面按钮点击事件
        id_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putCharSequence("handler", nickname);
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);       //点击进入详细页面
            }
        });

        tv_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putCharSequence("handler", nickname);
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);       //点击进入详细页面
            }
        });

        tv_s_contest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContestActivity.class));
            }
        });

        tv_s_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActionsActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        //读取数据
        SharedPreferences pref = getSharedPreferences("CodeForcePref", MODE_PRIVATE);
        String _handler = pref.getString("handler", "tourist");

        if(nickname == null || _handler.equals(nickname) == false)
        {
            (new InitMainThread(this)).start();
        }
    }
}
