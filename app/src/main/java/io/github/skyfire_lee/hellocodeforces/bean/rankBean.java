package io.github.skyfire_lee.hellocodeforces.bean;

/**
 * Created by SkyFire on 2016/5/20.
 */
public class rankBean {

    private String contestName;
    private String oldRating;
    private String newRating;
    private String rank;

    public rankBean(String contestName, String oldRating, String newRating, String rank) {
        this.contestName = contestName;
        this.oldRating = oldRating;
        this.newRating = newRating;
        this.rank = rank;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getOldRating() {
        return oldRating;
    }

    public void setOldRating(String oldRating) {
        this.oldRating = oldRating;
    }

    public String getNewRating() {
        return newRating;
    }

    public void setNewRating(String newRating) {
        this.newRating = newRating;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
