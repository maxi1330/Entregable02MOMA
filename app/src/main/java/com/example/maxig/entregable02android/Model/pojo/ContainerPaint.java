package com.example.maxig.entregable02android.Model.pojo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerPaint {

    @SerializedName("paints")
    private List<Paint> paints;

    public ContainerPaint(List<Paint> paints) {
        this.paints = paints;
    }

    public List<Paint> getPaints() {
        return paints;
    }
}
