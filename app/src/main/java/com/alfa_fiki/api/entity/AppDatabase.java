package com.alfa_fiki.api.entity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DataDiri.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DataDiriDAO dao();
    private static AppDatabase appDatabase;

    public static AppDatabase iniDb(Context context){
        if(appDatabase == null)
            appDatabase = Room.databaseBuilder(context, AppDatabase.class,
                    "dbUser").allowMainThreadQueries().build();

        return appDatabase;
    }
}

