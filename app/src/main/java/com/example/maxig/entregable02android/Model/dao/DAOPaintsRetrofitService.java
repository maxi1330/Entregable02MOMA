package com.example.maxig.entregable02android.Model.dao;

import com.example.maxig.entregable02android.Model.pojo.ContainerPaint;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DAOPaintsRetrofitService {

        @GET("/bins/x858r")
        Call<ContainerPaint> getPaints();

}
