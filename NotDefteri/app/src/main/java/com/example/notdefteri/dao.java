package com.example.notdefteri;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;
import java.util.List;

    @Dao
    public interface dao {
        @Insert
        public void notEkle(Not not);
        @Query("SELECT * FROM notlar")
        public List<Not> getNot();
        @Delete
        public void notSil(Not not);
        @Update
        public void notGuncelle(Not not);
    }
