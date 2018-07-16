package com.example.maxig.entregable02android.Controller;

import android.content.Context;

import com.example.maxig.entregable02android.Model.pojo.Artists;
import com.example.maxig.entregable02android.Model.pojo.Database;

import java.util.List;

public class ControllerRoomArtists {

    private Context context;

    public ControllerRoomArtists(Context context) {
        this.context = context;
    }

    public List<Artists> getArtists(){
        Database dataBase = new Database(context);
        return dataBase.getAllArtist();
    }

    public void addArtist(Artists artists){
        Database dataBase = new Database(context);
        dataBase.insertAll(artists);
    }

    public void removeArtist(Artists artists){
        Database dataBase = new Database(context);
        dataBase.delete(artists);
    }
}
