package com.example.maxig.entregable02android.Model.pojo;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.maxig.entregable02android.Util.RoomAppDatabase;

import java.util.List;

public class Database {
    private RoomAppDatabase db;

    public Database(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    //DAO Artist
    public List<Artists> getAllArtist(){
        return db.artistDao().getAllArtists();
    }

    public void insertAll(Artists... artists){
        db.artistDao().insertAll(artists);
    }

    public void delete(Artists artists){
        db.artistDao().delete(artists);
    }

}
