package com.example.praty.stackclone.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.praty.stackclone.model.QuestionsTitle;

import java.util.List;


//Data access object class
@Dao
public interface QuestionDao {

    @Insert
    long[] insertQuestions(QuestionsTitle... questions);

    @Query("SELECT * FROM questions")
    LiveData<List<QuestionsTitle>> getQuestions();

    @Query("DELETE FROM questions")
    void delete();
}
