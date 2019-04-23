package com.example.praty.stackclone.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//class for the RoomDatabase (Entity class)
@Entity(tableName = "questions")
public class QuestionsTitle {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "title")
    private String questionTitle;

    public QuestionsTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public QuestionsTitle(){

    }
}
