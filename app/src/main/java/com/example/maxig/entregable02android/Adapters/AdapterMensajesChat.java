package com.example.maxig.entregable02android.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maxig.entregable02android.Model.pojo.Mensaje;
import com.example.maxig.entregable02android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdapterMensajesChat extends RecyclerView.Adapter {

    private List<Mensaje> listadoDeMensajes;
    private FirebaseUser user;

    public AdapterMensajesChat() {
        this.listadoDeMensajes = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_mensaje_chat_recibido,parent,false);
        AdapterMensajesChat.mensajeViewHolder mensajeViewHolder = new AdapterMensajesChat.mensajeViewHolder(celda);
        return mensajeViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if(listadoDeMensajes.get(position).getNameUser().equals(user.getDisplayName())) {
            return R.layout.celda_mensaje_chat_enviado;
        }else {
            return R.layout.celda_mensaje_chat_recibido;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mensaje unMensaje = listadoDeMensajes.get(position);
        mensajeViewHolder mensajeViewHolder = (mensajeViewHolder) holder;
        mensajeViewHolder.cargarDatos(unMensaje);
    }

    @Override
    public int getItemCount() {
        return listadoDeMensajes.size();
    }

    public void agregarMensaje(Mensaje unMensaje){
        listadoDeMensajes.add(unMensaje);
        notifyItemInserted(listadoDeMensajes.size());
    }

    public void agregarListaMensajes(List<Mensaje> listaMensaje){
        listadoDeMensajes.clear();
        listadoDeMensajes.addAll(listaMensaje);
        notifyDataSetChanged();
    }

    private class mensajeViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewNameUserChat;
        private TextView textViewHoraClock;
        private TextView textViewMensajeChat;

        public mensajeViewHolder(View itemView) {
            super(itemView);
            textViewNameUserChat = itemView.findViewById(R.id.textViewNameUserChat);
            textViewHoraClock = itemView.findViewById(R.id.textViewClockMensaje);
            textViewMensajeChat = itemView.findViewById(R.id.textViewMensajeAEnviar);
        }

        public void cargarDatos(Mensaje unMensaje){
            textViewNameUserChat.setText(unMensaje.getNameUser());
            //textViewHoraClock.setText(unMensaje.getClock());
            textViewMensajeChat.setText(unMensaje.getMensaje());
        }
    }
}
