package com.example.maxig.entregable02android.View;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxig.entregable02android.Adapters.AdapterMensajesChat;
import com.example.maxig.entregable02android.Model.pojo.Mensaje;
import com.example.maxig.entregable02android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class ActivityChat extends AppCompatActivity {

    private EditText editTextMensajeChat;
    private ImageView imageViewBotonEnviar;
    private RecyclerView recyclerViewChat;
    private AdapterMensajesChat adapterMensajesChat;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editTextMensajeChat = findViewById(R.id.editTextMensajeChat);
        imageViewBotonEnviar = findViewById(R.id.imageViewBotonEnviar);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        adapterMensajesChat = new AdapterMensajesChat();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewChat.setLayoutManager(layoutManager);
        recyclerViewChat.setAdapter(adapterMensajesChat);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("CHAT");

        imageViewBotonEnviar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String mensaje = editTextMensajeChat.getText().toString();
                String nameUser = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
                //String date = "Aca va la hora, pero no me anda";                                      //En algunos celulares no anda el SimpleDateFormat y uso esto
                editTextMensajeChat.setText("");
                databaseReference.push().setValue(new Mensaje(mensaje, nameUser, date));
                recyclerViewChat.scrollToPosition(adapterMensajesChat.getItemCount()+1);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mensaje unMensaje = dataSnapshot.getValue(Mensaje.class);
                adapterMensajesChat.agregarMensaje(unMensaje);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
