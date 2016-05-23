package io.github.skyfire_lee.hellocodeforces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import io.github.skyfire_lee.hellocodeforces.bean.userInfoBean;


public class MainActivity extends AppCompatActivity {
    private ImageView id_avatar;
    private TextView id_name;
    private TextView id_rating;
    private TextView tv_rating;
    private TextView tv_s_contest;
    private TextView tv_s_blog;
    private LineChart lc_charts;
    private Handler handler;
    private String mhandler;

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
                bundle.putCharSequence("handler", mhandler);
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);       //点击进入详细页面
            }
        });

        tv_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putCharSequence("handler", mhandler);
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
                startActivity(new Intent(MainActivity.this, BlogActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        //读取数据
        SharedPreferences pref = getSharedPreferences("cfPref", MODE_PRIVATE);
        String xhandler = pref.getString("handler", "tourist");

        if(mhandler == null || xhandler.equals(mhandler) == false)
        {
            mhandler = xhandler;
            //更新界面数据线程
            (new Thread(new Runnable() {
                @Override
                public void run() {

                    final Bitmap bitmap = SuperUtils.getAvatarImage(mhandler);

                    final userInfoBean userInfo = SuperUtils.getUserInfo(mhandler);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            id_avatar.setImageBitmap(bitmap);
                            id_name.setText(mhandler);
                            id_rating.setText(userInfo.getRating());
                        }
                    });
                }
            })).start();
        }

    }


}
