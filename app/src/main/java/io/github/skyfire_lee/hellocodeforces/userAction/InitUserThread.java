package io.github.skyfire_lee.hellocodeforces.userAction;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.UserActivity;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;
import io.github.skyfire_lee.hellocodeforces.bean.userInfoBean;

/**
 * Created by SkyFire on 2016/5/18.
 */
public class InitUserThread extends Thread {

    private UserActivity context;

    public InitUserThread(UserActivity context) {
        this.context = context;
    }

    @Override
    public void run() {

        JSONArray jsonArray;

        final JSONObject jsonObject;

        try {
            String doc = SuperUtils.getHTML("http://codeforces.com/api/user.info?handles=" + context._handler);

            jsonArray = new JSONObject(doc).getJSONArray("result");

            jsonObject = jsonArray.getJSONObject(0);

        //    final userInfoBean userInfoBean = new userInfoBean(jsonObject.getString("handle"), jsonObject.getString("rank"), jsonObject.getString("rating"));
            final userInfoBean userInfoBean = SuperUtils.getUserInfo(context._handler);

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        context.account.add(new itemBean(SuperUtils.getChinese("handle", context), userInfoBean.getHandle()));
                        context.account.add(new itemBean(SuperUtils.getChinese("rank", context), userInfoBean.getRank()));
                        context.account.add(new itemBean(SuperUtils.getChinese("rating", context), userInfoBean.getRating()));

                        Iterator<?> it = jsonObject.keys();
                        while (it.hasNext()) {
                            String key = (String) it.next().toString();

                            if (key.equals("handle")) continue;
                            if (key.equals("rank")) continue;
                            if (key.equals("rating")) continue;
                            if (key.equals("titlePhoto")) continue;
                            if (key.equals("avatar")) continue;

                            String value = jsonObject.getString(key);

                            context.info.add(new itemBean(SuperUtils.getChinese(key, context), value));
                        }

                        context.accountAdapter.notifyDataSetChanged();
                        context.infoAdapter.notifyDataSetChanged();
                        context.load.setVisibility(View.GONE);
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
