package io.github.skyfire_lee.hellocodeforces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.github.skyfire_lee.hellocodeforces.bean.userInfoBean;

/**
 * Created by SkyFire on 2016/5/19.
 */
public class SuperUtils {
    public static String getChinese(String str, Context context) {
        if (str.equals("handle")) return context.getString(R.string.handle);
        if (str.equals("friendOfCount")) return context.getString(R.string.friendOfCount);
        if (str.equals("avatar")) return context.getString(R.string.avatar);
        if (str.equals("titlePhoto")) return context.getString(R.string.titlePhoto);
        if (str.equals("contribution")) return context.getString(R.string.contribution);
        if (str.equals("rank")) return context.getString(R.string.rank);
        if (str.equals("rating")) return context.getString(R.string.rating);
        if (str.equals("maxRank")) return context.getString(R.string.maxRank);
        if (str.equals("maxRating")) return context.getString(R.string.maxRating);
        if (str.equals("lastOnlineTimeSeconds"))
            return context.getString(R.string.lastOnlineTimeSeconds);
        if (str.equals("registrationTimeSeconds"))
            return context.getString(R.string.registrationTimeSeconds);
        if (str.equals("firstName")) return context.getString(R.string.firstName);
        if (str.equals("lastName")) return context.getString(R.string.lastName);
        if (str.equals("country")) return context.getString(R.string.country);
        if (str.equals("city")) return context.getString(R.string.city);
        if (str.equals("organization")) return context.getString(R.string.organization);
        return str;
    }

    public static Bitmap getWebImage(String url) {
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap getAvatarImage(String handler) {
        Bitmap bitmap;

        JSONArray jsonArray;

        final JSONObject jsonObject;

        String doc = null;
        try {
            doc = Jsoup.connect("http://codeforces.com/api/user.info?handles=" + handler).ignoreContentType(true).execute().body();

            jsonArray = new JSONObject(doc).getJSONArray("result");

            jsonObject = jsonArray.getJSONObject(0);

            bitmap = getWebImage(jsonObject.getString("titlePhoto"));

            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static userInfoBean getUserInfo(String handler) {
        JSONArray jsonArray;

        final JSONObject jsonObject;

        String doc = null;
        try {
            doc = Jsoup.connect("http://codeforces.com/api/user.info?handles=" + handler).ignoreContentType(true).execute().body();

            jsonArray = new JSONObject(doc).getJSONArray("result");

            jsonObject = jsonArray.getJSONObject(0);

            final userInfoBean userInfoBean = new userInfoBean(jsonObject.getString("handle"), jsonObject.getString("rank"), jsonObject.getString("rating"));

            return userInfoBean;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
