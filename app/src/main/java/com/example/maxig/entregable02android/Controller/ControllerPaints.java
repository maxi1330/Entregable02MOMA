package com.example.maxig.entregable02android.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.maxig.entregable02android.Model.dao.DAOPaintsInternet;
import com.example.maxig.entregable02android.Model.pojo.Paint;
import com.example.maxig.entregable02android.Util.ResultListener;

import java.util.List;

public class ControllerPaints {

    Context context;

    public ControllerPaints(Context context) {
        this.context = context;
    }

    public void obtenerObrasArte (final ResultListener<List<Paint>> escuchadorVista){

        if(hayInternet()){                                                                          //Si hay internet busco en retrofit
            ResultListener<List<Paint>> escuchadorControlador = new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    ControllerRoomPaints controllerRoomPaints = new ControllerRoomPaints(context);
                    for (Paint unPaint : resultado ) {                                              //Actualizo la lista en Room
                        controllerRoomPaints.removePaint(unPaint);
                        controllerRoomPaints.addPaint(unPaint);
                    }
                    escuchadorVista.finish(resultado);
                }
            };

            DAOPaintsInternet daoPaintsInternet = new DAOPaintsInternet();
            daoPaintsInternet.obtenerPinturas(escuchadorControlador);
        }
        else {                                                                                     //Sino en room
            ControllerRoomPaints controllerRoomPaints = new ControllerRoomPaints(context);
            escuchadorVista.finish(controllerRoomPaints.getPaints());
        }
    }

    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Toast.makeText(context, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            //Toast.makeText(context, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
