package io.github.skyfire_lee.hellocodeforces.bean;

/**
 * Created by SkyFire on 2016/5/21.
 */
public class blogBean {
    private String title;
    private String author;
    private String creationTimeSeconds;

    public String getBlogEntryId() {
        return blogEntryId;
    }

    public blogBean setBlogEntryId(String blogEntryId) {
        this.blogEntryId = blogEntryId;
        return this;
    }

    private String blogEntryId;

    public String getTitle() {
        return title;
    }

    public blogBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public blogBean setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public blogBean setCreationTimeSeconds(String creationTimeSeconds) {
        this.creationTimeSeconds = creationTimeSeconds;
        return this;
    }
}
