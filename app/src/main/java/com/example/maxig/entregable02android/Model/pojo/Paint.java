package com.example.maxig.entregable02android.Model.pojo;

public class Paint {

    private String image;
    private String name;
    private String artistId;

    public Paint(String image, String name, String artistId) {
        this.image = image;
        this.name = name;
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return "Paint{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}
