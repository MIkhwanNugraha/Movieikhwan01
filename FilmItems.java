package com.dicoding.associate.cataloguemovie;

import org.json.JSONObject;

public class FilmItems {
    private int id;
    private String nama;
    private String detail;
    private String tanggal;
    private String img;

    private final static String POSTER_URL = "http://image.tmdb.org/t/p/w185";

    public FilmItems(JSONObject object){

        try {
            int id = object.getInt("id");
            String name = object.getString("name");
            String detail = object.getJSONArray("film").getJSONObject(0).getString("detail");
            String tanggal = object.getJSONArray("film").getJSONObject(0).getString("tanggal");
            String img = object.getString("img");

            this.id = id;
            this.nama = name;;
            this.detail = detail;
            this.tanggal = tanggal;
            this.img = img;

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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
