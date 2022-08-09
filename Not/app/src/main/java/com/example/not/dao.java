package com.example.not;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface dao {

    @Insert
    public void  notEkle(Not not);

    @Query("select * from notlar")
    public List<Not>getNot();

    @Delete
    public void notSil(Not not);

    @Update
    public void notGuncelle(Not not);
}
