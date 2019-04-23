package com.example.praty.stackclone.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionResponse {

    @SerializedName("items")
    private List<Questions> items;

    @SerializedName("has_more")
    private boolean has_more;

    @SerializedName("quota_max")
    private int quota_max;

    @SerializedName("quota_remaining")
    private int quota_remaining;

    public QuestionResponse(List<Questions> items, boolean has_more, int quota_max, int quota_remaining) {
        this.items = items;
        this.has_more = has_more;
        this.quota_max = quota_max;
        this.quota_remaining = quota_remaining;
    }

    public QuestionResponse() {
    }

    public List<Questions> getItems() {
        return items;
    }

    public void setItems(List<Questions> items) {
        this.items = items;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(int quota_max) {
        this.quota_max = quota_max;
    }

    public int getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(int quota_remaining) {
        this.quota_remaining = quota_remaining;
    }
}
