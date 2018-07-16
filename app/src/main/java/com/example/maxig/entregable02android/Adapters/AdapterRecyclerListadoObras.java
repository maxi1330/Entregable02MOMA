package com.example.maxig.entregable02android.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maxig.entregable02android.Model.pojo.Paint;
import com.example.maxig.entregable02android.R;
import com.example.maxig.entregable02android.Util.GlideApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class AdapterRecyclerListadoObras extends RecyclerView.Adapter {

    private List<Paint> listadoDeObras;
    private EscuchadorPinturas escuchadorPinturas;

    public AdapterRecyclerListadoObras(EscuchadorPinturas escuchadorPinturas) {
        this.listadoDeObras = new ArrayList<>();
        this.escuchadorPinturas = escuchadorPinturas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_recycler_listado_obras,parent,false);
        ObrasViewHolder obrasViewHolder = new ObrasViewHolder(celda);
        return obrasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Paint unPaint = listadoDeObras.get(position);
        ObrasViewHolder obrasViewHolder = (ObrasViewHolder) holder;
        obrasViewHolder.cargarDatos(unPaint);
    }

    @Override
    public int getItemCount() {
        return listadoDeObras.size();
    }

    public void actualizarLista(List<Paint> lista){
        listadoDeObras.clear();
        listadoDeObras.addAll(lista);
        notifyDataSetChanged();
    }

    private class ObrasViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitulo;
        ImageView imageViewPaintListado;

        public ObrasViewHolder(final View itemView) {
            super(itemView);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            imageViewPaintListado = itemView.findViewById(R.id.imagePaintListado);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    escuchadorPinturas.envioPaint(listadoDeObras.get(getAdapterPosition()));
                }
            });
        }

        public void cargarDatos(Paint unPaint){
            textViewTitulo.setText(unPaint.getName());
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRefPintura = storage.getReference().child(unPaint.getImage());
            GlideApp.with(itemView).load(storageRefPintura).centerCrop().into(imageViewPaintListado);
        }
    }

    public interface EscuchadorPinturas{
        void envioPaint(Paint paint);
    }

}
