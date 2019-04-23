package com.example.praty.stackclone.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.content.Context;

import com.example.praty.stackclone.asynctasks.DeleteAsyncTask;
import com.example.praty.stackclone.asynctasks.InsertAsyncTask;
import com.example.praty.stackclone.model.Questions;
import com.example.praty.stackclone.model.QuestionsTitle;

import java.util.List;

//Intermediate class for the database, DAO and QuestionsDatabase
public class QuestionRepository {

    private QuestionsDatabase mDatabase;


    public QuestionRepository(Context context) {
        mDatabase= QuestionsDatabase.getInstance(context);
    }

    public void insertTask(QuestionsTitle questions){
        new InsertAsyncTask(mDatabase.getDao()).execute(questions);

    }


    public LiveData<List<QuestionsTitle>> retrieveNotesTask(){
        return mDatabase.getDao().getQuestions();
    }

    public void deleteTask(){
        new DeleteAsyncTask(mDatabase.getDao()).execute();
    }
}
