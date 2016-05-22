package io.github.skyfire_lee.hellocodeforces.bean;

import java.security.Key;

/**
 * Created by SkyFire on 2016/5/18.
 */
public class itemBean {
    public itemBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String key;
    private String value;

}
