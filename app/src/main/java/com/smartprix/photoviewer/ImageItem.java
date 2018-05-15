package com.smartprix.photoviewer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * This is and will always be a test run.
 * Unless its being submitted to the Play Store, in which case, God bless your code.
 * Created by Manu Srivastava on 5/15/18.
 */
public class ImageItem implements Serializable {
    private String url;
    private Boolean liked;
    private String title;
    private String tags;

    private int widthMeta;
    private int heightMeta;
    private int widthToShow;
    private int heightToShow;

    public ImageItem(String url, Boolean liked, String tags, int widthMeta, int heightMeta, int widthToShow, int heightToShow) {
        this.url = url;
        this.liked = liked;
        this.tags = tags;
        this.widthMeta = widthMeta;
        this.heightMeta = heightMeta;
        this.widthToShow = widthToShow;
        this.heightToShow = heightToShow;
        this.tags = tags;
        this.title = title;
    }

    public ImageItem(String url, Boolean liked, int widthMeta, int heightMeta) {
        this.url = url;
        this.liked = liked;
        this.widthMeta = widthMeta;
        this.heightMeta = heightMeta;
        this.widthToShow = widthMeta > heightMeta ? heightMeta : widthMeta;
        this.heightToShow = widthMeta > heightMeta ? heightMeta : widthMeta;
    }

    public ImageItem(JSONObject jsonItem) throws JSONException {
        this.url = jsonItem.getString("url");
        this.heightMeta = jsonItem.getInt("height");
        this.widthMeta = jsonItem.getInt("width");
        this.widthToShow = widthMeta > heightMeta ? heightMeta : widthMeta;
        this.heightToShow = widthMeta > heightMeta ? heightMeta : widthMeta;
        this.tags = jsonItem.getString("tags");
        this.title = jsonItem.getString("title");
    }

    public String description() {
        return "\nURL : " + this.getUrl() +
                "\nTITLE : " + this.getTitle() +
                "\nTAGS : " + this.getTags() +
                "\nWIDTH : " + this.getWidthMeta() +
                "\nHEIGHT : " + this.getHeightMeta();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getLiked() {
        return (liked == null ? false : liked);
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public int getWidthMeta() {
        return widthMeta;
    }

    public void setWidthMeta(int widthMeta) {
        this.widthMeta = widthMeta;
    }

    public int getHeightMeta() {
        return heightMeta;
    }

    public void setHeightMeta(int heightMeta) {
        this.heightMeta = heightMeta;
    }

    public int getWidthToShow() {
        return widthToShow;
    }

    public void setWidthToShow(int widthToShow) {
        this.widthToShow = widthToShow;
    }

    public int getHeightToShow() {
        return heightToShow;
    }

    public void setHeightToShow(int heightToShow) {
        this.heightToShow = heightToShow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
