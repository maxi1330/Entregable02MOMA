package com.example.maxig.entregable02android.Model.pojo;

public class Artists {
    private String Influenced_by;
    private String artistId;
    private String name;
    private String nacionality;

    public Artists() {
    }

    public Artists(String influenced_by, String artistId, String name, String nacionality) {

        Influenced_by = influenced_by;
        this.artistId = artistId;
        this.name = name;
        this.nacionality = nacionality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    public void setInfluenced_by(String influenced_by) {
        Influenced_by = influenced_by;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "Influenced_by='" + Influenced_by + '\'' +
                ", artistId='" + artistId + '\'' +
                ", name='" + name + '\'' +
                ", nacionality='" + nacionality + '\'' +
                '}';
    }
}
