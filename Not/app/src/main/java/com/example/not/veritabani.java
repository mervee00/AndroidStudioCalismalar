package com.example.not;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {Not.class},version = 1)
public abstract class veritabani extends RoomDatabase {
    public abstract dao dao();
}
