package io.github.skyfire_lee.hellocodeforces.bean;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class contestBean {
    private String id;
    private String name;
    private String type;
    private String phase;
    private String frozen;
    private String durationSeconds;
    private String startTimeSeconds;
    private String relativeTimeSeconds;

    public String getId() {
        return id;
    }

    public contestBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public contestBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public contestBean setType(String type) {
        this.type = type;
        return this;
    }

    public String getPhase() {
        return phase;
    }

    public contestBean setPhase(String phase) {
        this.phase = phase;
        return this;
    }

    public String getFrozen() {
        return frozen;
    }

    public contestBean setFrozen(String frozen) {
        this.frozen = frozen;
        return this;
    }

    public String getDurationSeconds() {
        return durationSeconds;
    }

    public contestBean setDurationSeconds(String durationSeconds) {
        this.durationSeconds = durationSeconds;
        return this;
    }

    public String getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public contestBean setStartTimeSeconds(String startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
        return this;
    }

    public String getRelativeTimeSeconds() {
        return relativeTimeSeconds;
    }

    public contestBean setRelativeTimeSeconds(String relativeTimeSeconds) {
        this.relativeTimeSeconds = relativeTimeSeconds;
        return this;
    }
}
