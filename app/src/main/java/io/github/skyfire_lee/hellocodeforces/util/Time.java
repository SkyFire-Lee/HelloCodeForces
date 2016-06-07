package io.github.skyfire_lee.hellocodeforces.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SkyFire on 2016/6/7.
 */
public class Time {

    public static String getDateToString(long time) {
        Date d = new Date(time*1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
}
