package com.example.contact_gurindersingh_c0806087_android.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class PhoneBookRoomDatabase extends RoomDatabase {
    private static final String DB_DATABASE = "phonebook_database";
    public abstract PhoneBookDao phoneBookDao();
    private static volatile PhoneBookRoomDatabase INSTANCE;
    public static PhoneBookRoomDatabase getInstance(Context context){
        if (INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PhoneBookRoomDatabase.class, DB_DATABASE)
                    .allowMainThreadQueries()
                    .build();
        return INSTANCE;
    }
}
