package com.example.maxig.entregable02android.Controller;

import android.content.Context;
import com.example.maxig.entregable02android.Model.pojo.Database;
import com.example.maxig.entregable02android.Model.pojo.DatabasePaints;
import com.example.maxig.entregable02android.Model.pojo.Paint;

import java.util.List;

public class ControllerRoomPaints {

    private Context context;

    public ControllerRoomPaints(Context context) {
        this.context = context;
    }

    public List<Paint> getPaints(){
        DatabasePaints dataBase = new DatabasePaints(context);
        return dataBase.getAllPaints();
    }

    public void addPaint(Paint paint){
        DatabasePaints database = new DatabasePaints(context);
        database.insertAll(paint);
    }

    public void removePaint(Paint paint){
        DatabasePaints database = new DatabasePaints(context);
        database.delete(paint);
    }
}
