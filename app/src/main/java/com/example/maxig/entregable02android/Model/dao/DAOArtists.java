package com.example.maxig.entregable02android.Model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.maxig.entregable02android.Model.pojo.Artists;

import java.util.List;

@Dao
public interface DAOArtists {

    @Query("SELECT * FROM artists")
    List<Artists> getAllArtists();

    @Query("SELECT * FROM artists WHERE artistId Like :id")
    Artists getArtistWithId(String id);

    @Insert
    void insertAll(Artists... artist);

    @Delete
    void delete(Artists artist);
}
