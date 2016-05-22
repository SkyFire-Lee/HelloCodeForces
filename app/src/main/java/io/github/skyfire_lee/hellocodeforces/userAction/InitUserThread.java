package io.github.skyfire_lee.hellocodeforces.userAction;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import io.github.skyfire_lee.hellocodeforces.SuperUtils;
import io.github.skyfire_lee.hellocodeforces.bean.itemBean;
import io.github.skyfire_lee.hellocodeforces.bean.userInfoBean;

/**
 * Created by SkyFire on 2016/5/18.
 */
public class InitUserThread extends Thread {
    private List<itemBean> account;
    private List<itemBean> info;
    private Handler handler;
    private InfoAdapter accountAdapter;
    private InfoAdapter infoAdapter;
    private String mhandler;
    private Context context;

    public InitUserThread(List<itemBean> account, List<itemBean> info, Handler handler, InfoAdapter accountAdapter, InfoAdapter infoAdapter, String mhandler, Context context) {
        this.account = account;
        this.info = info;
        this.handler = handler;
        this.accountAdapter = accountAdapter;
        this.infoAdapter = infoAdapter;
        this.mhandler = mhandler;
        this.context = context;
    }

    @Override
    public void run() {

        JSONArray jsonArray;

        final JSONObject jsonObject;

        try {
            String doc = Jsoup.connect("http://codeforces.com/api/user.info?handles=" + mhandler).ignoreContentType(true).execute().body();

            jsonArray = new JSONObject(doc).getJSONArray("result");

            jsonObject = jsonArray.getJSONObject(0);

            final userInfoBean userInfoBean = new userInfoBean(jsonObject.getString("handle"), jsonObject.getString("rank"), jsonObject.getString("rating"));

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        account.add(new itemBean(SuperUtils.getChinese("handle", context), userInfoBean.getHandle()));
                        account.add(new itemBean(SuperUtils.getChinese("rank", context), userInfoBean.getRank()));
                        account.add(new itemBean(SuperUtils.getChinese("rating", context), userInfoBean.getRating()));

                        Iterator<?> it = jsonObject.keys();
                        while (it.hasNext()) {
                            String key = (String) it.next().toString();

                            if (key.equals("handle")) continue;
                            if (key.equals("rank")) continue;
                            if (key.equals("rating")) continue;
                            if (key.equals("titlePhoto")) continue;
                            if (key.equals("avatar")) continue;

                            String value = jsonObject.getString(key);

                            info.add(new itemBean(SuperUtils.getChinese(key, context), value));
                        }

                        accountAdapter.notifyDataSetChanged();
                        infoAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
