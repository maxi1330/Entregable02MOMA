package com.example.maxig.entregable02android.Model.pojo;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.maxig.entregable02android.Util.RoomAppDatabase;

import java.util.List;

public class DatabasePaints {
    private RoomAppDatabase db;

    public DatabasePaints(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    public List<Paint> getAllPaints(){
        return db.paintsDao().getAllPaints();
    }

    public void insertAll(Paint... paints){
        db.paintsDao().insertAll(paints);
    }

    public void delete(Paint paints){
       db.paintsDao().delete(paints);
    }
}
