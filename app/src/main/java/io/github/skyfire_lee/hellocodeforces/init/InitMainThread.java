package io.github.skyfire_lee.hellocodeforces.init;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.skyfire_lee.hellocodeforces.ui.MainActivity;
import io.github.skyfire_lee.hellocodeforces.util.Network;

/**
 * Created by SkyFire on 2016/5/30.
 */
public class InitMainThread extends Thread {

    private MainActivity context;

    public InitMainThread(MainActivity context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            SharedPreferences pref = context.getSharedPreferences("CodeForcePref", context.MODE_PRIVATE);

            final String handler = pref.getString("handler", "tourist");

            final Bitmap bitmap = Network.getAvatarImage(handler);

            String doc = Network.getHTML("http://codeforces.com/api/user.info?handles=" + handler);

            final JSONObject userObject = (new JSONObject(doc).getJSONArray("result")).getJSONObject(0);

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        context.handle = handler;
                        context.avatarIV.setImageBitmap(bitmap);
                        context.handleID.setText(handler);

                        context.ratingTV.setText(userObject.getString("rating"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
