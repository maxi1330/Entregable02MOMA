package com.example.maxig.entregable02android.View;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxig.entregable02android.Controller.ControllerArtists;
import com.example.maxig.entregable02android.Controller.ControllerRoomArtists;
import com.example.maxig.entregable02android.Model.pojo.Artists;
import com.example.maxig.entregable02android.R;
import com.example.maxig.entregable02android.Util.GlideApp;
import com.example.maxig.entregable02android.Util.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetalleObras extends AppCompatActivity {
    public static final String NAME_PAINT  = "NAME";
    public static final String ARTIST_ID  = "ARTIST_ID";
    public static final String IMAGE_URL  = "IMAGE_URL";

    private String namePaint;
    private String imageURL;
    private String artistID;

    private ImageView imageViewPaint;
    private TextView textViewNamePaint;
    private TextView textViewArtistName;
    private TextView textViewArtistNacionality;
    private TextView textViewArtistInfluence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_obras);

        imageViewPaint = findViewById(R.id.imageViewPaint);
        textViewArtistName = findViewById(R.id.textViewArtistName);
        textViewNamePaint = findViewById(R.id.textViewNamePaint);
        textViewArtistNacionality = findViewById(R.id.textViewArtistNacionality);
        textViewArtistInfluence = findViewById(R.id.textViewArtistInfluence);

        Bundle bundle = getIntent().getExtras();
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        if(bundle != null){
            namePaint = bundle.getString(NAME_PAINT);
            imageURL = bundle.getString(IMAGE_URL);
            artistID = bundle.getString(ARTIST_ID);
        }
        ActualizoDatos();
    }

    private void ActualizoDatos() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRefPintura = storage.getReference().child(imageURL);
        GlideApp.with(ActivityDetalleObras.this).load(storageRefPintura).centerCrop().into(imageViewPaint);
        CargoDatosArtista();
    }

    private void CargoDatosArtista() {
        ControllerArtists controllerArtists = new ControllerArtists(getApplicationContext());
        controllerArtists.obtenerArtista(new ResultListener<List<Artists>>() {
            @Override
            public void finish(List<Artists> resultado) {
                for (Artists unArtista : resultado ) {
                    if(unArtista.getArtistId().equals(artistID)){
                        textViewNamePaint.setText(namePaint);
                        textViewArtistName.setText(unArtista.getName());
                        textViewArtistInfluence.setText(unArtista.getInfluenced_by());
                        textViewArtistNacionality.setText(unArtista.getNationality());
                        return;
                    }
                }
                Toast.makeText(ActivityDetalleObras.this, "Artista NO encontrado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
