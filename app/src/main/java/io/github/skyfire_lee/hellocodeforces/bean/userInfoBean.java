package io.github.skyfire_lee.hellocodeforces.bean;

/**
 * Created by SkyFire on 2016/5/19.
 */
public class userInfoBean {
    private String handle;
    private String friendOfCount;
    private String avatar;
    private String titlePhoto;
    private String contribution;
    private String rank;
    private String rating;
    private String maxRank;
    private String maxRating;
    private String lastOnlineTimeSeconds;
    private String registrationTimeSeconds;

    public userInfoBean(String handle, String rank, String rating) {
        this.handle = handle;
        this.rank = rank;
        this.rating = rating;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getRegistrationTimeSeconds() {
        return registrationTimeSeconds;
    }

    public void setRegistrationTimeSeconds(String registrationTimeSeconds) {
        this.registrationTimeSeconds = registrationTimeSeconds;
    }

    public String getLastOnlineTimeSeconds() {
        return lastOnlineTimeSeconds;
    }

    public void setLastOnlineTimeSeconds(String lastOnlineTimeSeconds) {
        this.lastOnlineTimeSeconds = lastOnlineTimeSeconds;
    }

    public String getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(String maxRating) {
        this.maxRating = maxRating;
    }

    public String getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(String maxRank) {
        this.maxRank = maxRank;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFriendOfCount() {
        return friendOfCount;
    }

    public void setFriendOfCount(String friendOfCount) {
        this.friendOfCount = friendOfCount;
    }
}


