package com.example.praty.stackclone.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Questions {

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("owner")
    private Owners owner;

    @SerializedName("is_answered")
    private boolean is_answered;

    @SerializedName("view_count")
    private int view_count;

    @SerializedName("answer_count")
    private int answer_count;

    @SerializedName("score")
    private int score;

    @SerializedName("last_activity_date")
    private String last_activity_date;

    @SerializedName("creation_date")
    private String creation_date;

    @SerializedName("question_id")
    private String question_id;

    @SerializedName("link")
    private String link;

    @SerializedName("title")
    private String title;

    public Questions(List<String> tags, Owners owner, boolean is_answered, int view_count, int answer_count, int score, String last_activity_date, String creation_date, String question_id, String link, String title) {
        this.tags = tags;
        this.owner = owner;
        this.is_answered = is_answered;
        this.view_count = view_count;
        this.answer_count = answer_count;
        this.score = score;
        this.last_activity_date = last_activity_date;
        this.creation_date = creation_date;
        this.question_id = question_id;
        this.link = link;
        this.title = title;
    }



    public Questions() {
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(String last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
