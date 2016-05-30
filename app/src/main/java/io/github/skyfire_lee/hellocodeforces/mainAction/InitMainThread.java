package io.github.skyfire_lee.hellocodeforces.mainAction;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

import io.github.skyfire_lee.hellocodeforces.MainActivity;
import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.bean.userInfoBean;

/**
 * Created by SkyFire on 2016/5/30.
 */
public class InitMainThread extends Thread{

    private MainActivity context;

    public InitMainThread(MainActivity context) {
        this.context = context;
    }

    @Override
    public void run() {

        SharedPreferences pref = context.getSharedPreferences("CodeForcePref", context.MODE_PRIVATE);
        final String handler = pref.getString("handler", "tourist");

        final Bitmap bitmap = SuperUtils.getAvatarImage(handler);

        final userInfoBean userInfo = SuperUtils.getUserInfo(handler);

        context.handler.post(new Runnable() {
            @Override
            public void run() {
                context.nickname = handler;
                context.id_avatar.setImageBitmap(bitmap);
                context.id_name.setText(handler);
                context.id_rating.setText(userInfo.getRating());
            }
        });
    }
}
