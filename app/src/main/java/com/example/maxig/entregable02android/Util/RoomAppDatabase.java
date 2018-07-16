package com.example.maxig.entregable02android.Util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.maxig.entregable02android.Model.dao.DAOArtists;
import com.example.maxig.entregable02android.Model.dao.DAOPaints;
import com.example.maxig.entregable02android.Model.pojo.Artists;
import com.example.maxig.entregable02android.Model.pojo.Paint;

@Database(entities = {Artists.class, Paint.class}, version = 1)
public abstract class RoomAppDatabase extends RoomDatabase {
        public abstract DAOArtists artistDao();
        public abstract DAOPaints paintsDao();
}
