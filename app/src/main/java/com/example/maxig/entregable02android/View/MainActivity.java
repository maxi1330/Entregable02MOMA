package com.example.maxig.entregable02android.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.maxig.entregable02android.Adapters.AdapterRecyclerListadoObras;
import com.example.maxig.entregable02android.Controller.ControllerPaints;
import com.example.maxig.entregable02android.Model.pojo.Paint;
import com.example.maxig.entregable02android.R;
import com.example.maxig.entregable02android.Util.ResultListener;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterRecyclerListadoObras.EscuchadorPinturas{

    private RecyclerView recyclerViewListadoObras;
    private AdapterRecyclerListadoObras adapter;
    private FloatingActionButton button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());

        if(AccessToken.getCurrentAccessToken() == null){
            IrALaPantallaLogin();
        }

        recyclerViewListadoObras = findViewById(R.id.recyclerViewListadoObras);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        adapter = new AdapterRecyclerListadoObras(this);
        recyclerViewListadoObras.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewListadoObras.setLayoutManager(layoutManager);

        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ActivityChat.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        cargarListado();
    }

    private void cargarListado(){
        final ControllerPaints controllerPaints = new ControllerPaints(getApplicationContext());
        controllerPaints.obtenerObrasArte(new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                adapter.actualizarLista(resultado);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }

    private void IrALaPantallaLogin(){
        startActivity(new Intent(MainActivity.this,FacebookLoginActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnLogOut:
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                IrALaPantallaLogin();
                return true;
            case R.id.btnPerfil:
                startActivity(new Intent(this,ActivityPerfil.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void envioPaint(Paint paint) {
        Intent intent = new Intent(this, ActivityDetalleObras.class);
        Bundle bundle = new Bundle();
        bundle.putString(ActivityDetalleObras.IMAGE_URL , paint.getImage());
        bundle.putString(ActivityDetalleObras.NAME_PAINT, paint.getName());
        bundle.putString(ActivityDetalleObras.ARTIST_ID, paint.getArtistId());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
