package com.example.maxig.entregable02android.View;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

        adapterMensajesChat = new AdapterMensajesChat();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewChat.setLayoutManager(layoutManager);
        recyclerViewChat.setAdapter(adapterMensajesChat);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("CHAT");

        imageViewBotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensaje = editTextMensajeChat.getText().toString();
                String nameUser = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                String clock = String.valueOf(new Date().getTime());
                editTextMensajeChat.setText("");

                databaseReference.push().setValue(new Mensaje(mensaje, nameUser, clock));
                recyclerViewChat.scrollToPosition(adapterMensajesChat.getItemCount()-1);

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
