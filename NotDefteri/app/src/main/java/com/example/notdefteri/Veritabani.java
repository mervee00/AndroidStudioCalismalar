package com.example.notdefteri;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Not.class},version = 1)
public abstract class Veritabani extends RoomDatabase {
    public abstract dao dao();
}
