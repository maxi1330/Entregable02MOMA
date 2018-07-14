package com.example.maxig.entregable02android.Model.dao;
import com.example.maxig.entregable02android.Model.pojo.ContainerPaint;
import com.example.maxig.entregable02android.Model.pojo.Paint;
import com.example.maxig.entregable02android.Util.ResultListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOPaintsInternet {

    private Retrofit retrofit;
    private DAOPaintsRetrofitService daoPaintsRetrofitService;

    public DAOPaintsInternet(){
        //Retrofit usa OkHttpClient y pido un constructor
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
        daoPaintsRetrofitService = retrofit.create(DAOPaintsRetrofitService.class);
    }

    public void obtenerPeliculasInternet(final ResultListener<List<Paint>> escuchadorControlador){
        //TODO 1) Pedirle al servicio el metodo que devuelve una call con las pinturas
        Call<ContainerPaint> escuchadorRetrofit = daoPaintsRetrofitService.getPaints();
        //TODO 2) Al objeto call pedirle las peliculas
        escuchadorRetrofit.enqueue(new Callback<ContainerPaint>() {
            @Override
            public void onResponse(Call<ContainerPaint> call, Response<ContainerPaint> response) {
                //TODO   y cuando estas vuelven darselas al listenerControler con el finish
                ContainerPaint containerPaint = response.body();
                escuchadorControlador.finish(containerPaint.getPaints());
            }

            @Override
            public void onFailure(Call<ContainerPaint> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Paint>());
            }
        });
    }
}
