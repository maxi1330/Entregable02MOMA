package com.example.maxig.entregable02android.Model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.maxig.entregable02android.Model.pojo.Artists;
import com.example.maxig.entregable02android.Model.pojo.Paint;

import java.util.List;

@Dao
public interface DAOPaints {

    @Query("SELECT * FROM paint")
    List<Paint> getAllPaints();

    @Query("SELECT * FROM paint WHERE artistId Like :id")
    Paint getPaintWithArtistId(String id);

    @Insert
    void insertAll(Paint... paints);

    @Delete
    void delete(Paint paint);
}
