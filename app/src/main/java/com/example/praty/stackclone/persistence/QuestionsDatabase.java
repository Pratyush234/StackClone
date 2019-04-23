package com.example.praty.stackclone.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.praty.stackclone.model.Questions;
import com.example.praty.stackclone.model.QuestionsTitle;

@Database(entities = {QuestionsTitle.class} ,version = 1)
public abstract class QuestionsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="questions_db";

    private static QuestionsDatabase instance;

    static QuestionsDatabase getInstance(final Context context){
        if(instance==null){
            instance= Room.databaseBuilder(
                    context.getApplicationContext(),
                    QuestionsDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract QuestionDao getDao();

}
