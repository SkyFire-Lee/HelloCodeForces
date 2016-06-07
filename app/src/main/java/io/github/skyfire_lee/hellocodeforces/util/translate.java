package io.github.skyfire_lee.hellocodeforces.util;

import android.content.Context;

import io.github.skyfire_lee.hellocodeforces.R;

/**
 * Created by SkyFire on 2016/6/7.
 */
public class Translate {
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
}
