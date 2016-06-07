package io.github.skyfire_lee.hellocodeforces.init;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.github.skyfire_lee.hellocodeforces.util.Network;
import io.github.skyfire_lee.hellocodeforces.ui.UserActivity;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;
import io.github.skyfire_lee.hellocodeforces.util.Translate;

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

        final JSONObject infoObject;

        final JSONObject userObject;

        try {
            String doc = Network.getHTML("http://codeforces.com/api/user.info?handles=" + context._handle);

            infoObject = (new JSONObject(doc).getJSONArray("result")).getJSONObject(0);

            doc = Network.getHTML("http://codeforces.com/api/user.info?handles=" + context._handle);

            userObject = (new JSONObject(doc).getJSONArray("result")).getJSONObject(0);

            context.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        context.account.add(new itemBean(Translate.getChinese("handle", context), userObject.getString("handle")));
                        context.account.add(new itemBean(Translate.getChinese("rank", context), userObject.getString("rank")));
                        context.account.add(new itemBean(Translate.getChinese("rating", context), userObject.getString("rating")));

                        Iterator<?> it = infoObject.keys();
                        while (it.hasNext()) {
                            String key = (String) it.next().toString();

                            if (key.equals("handle")) continue;
                            if (key.equals("rank")) continue;
                            if (key.equals("rating")) continue;
                            if (key.equals("titlePhoto")) continue;
                            if (key.equals("avatar")) continue;

                            String value = infoObject.getString(key);

                            context.info.add(new itemBean(Translate.getChinese(key, context), value));
                        }

                        context.accountAdapter.notifyDataSetChanged();
                        context.infoAdapter.notifyDataSetChanged();
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
