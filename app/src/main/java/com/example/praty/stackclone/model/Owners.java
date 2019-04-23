package com.example.praty.stackclone.model;

import com.google.gson.annotations.SerializedName;

public class Owners {

    @SerializedName("reputation")
    private int reputation;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("user_type")
    private String user_type;

    @SerializedName("profile_image")
    private String profile_image;

    @SerializedName("display_name")
    private String display_name;

    @SerializedName("link")
    private String link;

    public Owners(int reputation, String user_id, String user_type, String profile_image, String display_name, String link) {
        this.reputation = reputation;
        this.user_id = user_id;
        this.user_type = user_type;
        this.profile_image = profile_image;
        this.display_name = display_name;
        this.link = link;
    }

    public Owners() {
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
