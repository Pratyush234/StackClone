package com.example.praty.stackclone.asynctasks;

import android.os.AsyncTask;

import com.example.praty.stackclone.persistence.QuestionDao;

public class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {
    
    
    private QuestionDao mDao;

    public DeleteAsyncTask(QuestionDao dao) {
        this.mDao=dao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        mDao.delete();
        return null;
    }
}
