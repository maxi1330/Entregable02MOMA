package com.example.maxig.entregable02android.Controller;

import com.example.maxig.entregable02android.Model.dao.DAOPaintsInternet;
import com.example.maxig.entregable02android.Model.pojo.Paint;
import com.example.maxig.entregable02android.Util.ResultListener;

import java.util.List;

public class ControllerPaints {

    public void obtenerObrasArte (final ResultListener<List<Paint>> escuchadorVista){
        //Creo escuchador del controlador
        ResultListener<List<Paint>> escuchadorControlador = new ResultListener<List<Paint>>() {
            @Override
            public void finish(List<Paint> resultado) {
                escuchadorVista.finish(resultado);
            }
        };

        DAOPaintsInternet daoPaintsInternet = new DAOPaintsInternet();
        daoPaintsInternet.obtenerPeliculasInternet(escuchadorControlador);

    }
}
