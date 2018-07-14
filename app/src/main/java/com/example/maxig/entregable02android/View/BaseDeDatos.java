package com.example.maxig.entregable02android.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.maxig.entregable02android.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BaseDeDatos extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_de_datos);
        button = findViewById(R.id.btnLeer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escribir(view);
            }
        });
    }


    public void escribir(View view){
        DatabaseReference mDataBase;                                                    //Base de datos
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();             //Busco la instancia de la base de datos
        mDataBase = firebaseDatabase.getReference();                                    //Busco la referencia

        DatabaseReference reference = mDataBase.child("artists").child("3");            //

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Mensaje mensajeLeido = dataSnapshot.getValue(Mensaje.class);
               // Toast.makeText(BaseDeDatos.this, mensajeLeido.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        reference.addValueEventListener(valueEventListener);
    }
}
