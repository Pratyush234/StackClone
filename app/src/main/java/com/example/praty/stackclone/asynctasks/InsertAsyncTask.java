package com.example.praty.stackclone.asynctasks;

import android.os.AsyncTask;

import com.example.praty.stackclone.model.Questions;
import com.example.praty.stackclone.model.QuestionsTitle;
import com.example.praty.stackclone.persistence.QuestionDao;

public class InsertAsyncTask extends AsyncTask<QuestionsTitle,Void, Void> {

    private QuestionDao mDao;

    public InsertAsyncTask(QuestionDao mDao) {
        this.mDao = mDao;
    }

    @Override
    protected Void doInBackground(QuestionsTitle... questions) {
        mDao.insertQuestions(questions);
        return null;
    }
}
