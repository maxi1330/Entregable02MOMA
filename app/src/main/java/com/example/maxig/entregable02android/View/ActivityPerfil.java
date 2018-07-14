package com.example.maxig.entregable02android.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maxig.entregable02android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityPerfil extends AppCompatActivity {

    private TextView textViewNameUser;
    private TextView textViewMailUser;
    private ImageView imageViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        textViewNameUser = findViewById(R.id.textViewNameUser);
        textViewMailUser = findViewById(R.id.textViewMailUser);
        imageViewUser = findViewById(R.id.imageUser);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            textViewNameUser.setText(user.getDisplayName());
            textViewMailUser.setText(user.getEmail());
        }
    }
}
