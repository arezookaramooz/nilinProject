package com.example.arezookaramooz.nilin.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("albumId")
    @Expose
    int albumId;
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("url")
    @Expose
    String url;
    @SerializedName("thumbnailUrl")
    @Expose
    String thumbnailUrl;

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {

        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
