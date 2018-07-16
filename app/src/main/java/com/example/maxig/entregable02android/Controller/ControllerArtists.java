package com.example.maxig.entregable02android.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.maxig.entregable02android.Model.pojo.Artists;
import com.example.maxig.entregable02android.Util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ControllerArtists {
    Context context;

    public ControllerArtists(Context context) {
        this.context = context;
    }

    public void obtenerArtista(final ResultListener<List<Artists>> escuchadorVista){

        if(hayInternet()){                                                                          //Si hay internet
            final ArrayList<Artists> listado = new ArrayList<>();
            DatabaseReference mDataBase;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            mDataBase = firebaseDatabase.getReference();
            DatabaseReference reference = mDataBase.child("artists");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ControllerRoomArtists controllerRoomArtists = new ControllerRoomArtists(context);
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()){
                        Artists artistaLeido = dataSnapshotChild.getValue(Artists.class);
                        listado.add(artistaLeido);

                        controllerRoomArtists.removeArtist(artistaLeido);
                        controllerRoomArtists.addArtist(artistaLeido);
                    }
                    //Toast.makeText(context, listado.toString(), Toast.LENGTH_SHORT).show();
                    escuchadorVista.finish(listado);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, "Error al cargar!", Toast.LENGTH_SHORT).show();
                }
            };
            reference.addValueEventListener(valueEventListener);


        } else {                                                                                    //Si no hay internet
            ControllerRoomArtists controllerRoomArtists = new ControllerRoomArtists(context);
            escuchadorVista.finish(controllerRoomArtists.getArtists());
        }
    }

    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Toast.makeText(context, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            //Toast.makeText(context, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
