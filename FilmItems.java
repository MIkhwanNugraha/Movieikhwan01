package com.dicoding.associate.cataloguemovie;

import org.json.JSONObject;

public class FilmItems {
    private String nama;
    private String detail;
    private String tanggal;
    private String img;



    public FilmItems(JSONObject object){

        try {
            String name = object.getString("title");
            String detail = object.getString("overview");
            String img = object.getString("poster_path");
            String tanggal = object.getString("release_date");


            this.nama = name;
            this.detail = detail;
            this.tanggal = tanggal;
            this.img = img;


        }catch (Exception e){

            e.printStackTrace();
        }
    }



    public String getNama() {
        return nama;
    }

    public void setNama (String nama){
        this.nama = nama;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
