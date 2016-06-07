package io.github.skyfire_lee.hellocodeforces.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.github.skyfire_lee.hellocodeforces.R;
import io.github.skyfire_lee.hellocodeforces.bean.userInfoBean;

/**
 * Created by SkyFire on 2016/5/19.
 */
public class Network {

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
            conn.setConnectTimeout(30000);
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
            doc = getHTML("http://codeforces.com/api/user.info?handles=" + handler);

            jsonArray = new JSONObject(doc).getJSONArray("result");

            jsonObject = jsonArray.getJSONObject(0);

            bitmap = getWebImage(jsonObject.getString("titlePhoto"));

            return bitmap;

        }catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getHtmlCss(String data)  {
        data = "<html><head><link rel=\"stylesheet\" href=\"http://st.codeforces.com/s/56354/css/ttypography.css\" type=\"text/css\" charset=\"utf-8\"><style>* {font-size:16px;line-height:20px;} p {color:#333;font-size:0.75em !important;}</style>" + data;
        data = data + "</head><body></body></html>";
        return data;
    }

    public static String getHTML(String _url)    {
        String html = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(_url).openConnection();

            InputStream is = conn.getInputStream();

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = -1;

            while ((len = is.read(buffer)) != -1)   os.write(buffer, 0, len);

            os.close();
            is.close();

            html = os.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }
}
