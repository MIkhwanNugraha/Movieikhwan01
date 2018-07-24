package com.dicoding.associate.cataloguemovie;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<FilmItems>>{
    private ArrayList<FilmItems> mData;
    private boolean mHasResult = false;

    private String mKumpulanFilm;


    public MyAsyncTaskLoader(final Context context, String kumpulanFilm){
        super(context);

        onContentChanged();
        this.mKumpulanFilm = kumpulanFilm;
    }

    @Override
    protected void onStartLoading(){
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(@Nullable final ArrayList<FilmItems> data){
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset(){
        super.onReset();
        onStopLoading();
        if (mHasResult){
            onReleaseResource(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private static final String API_KEY = "5df38ae7b1c2fc4aa915020e359d7a52";

    @Nullable
    @Override
    public ArrayList<FilmItems> loadInBackground(){
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<FilmItems> filmItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" +API_KEY + "&language=en-US&query=" + mKumpulanFilm;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart(){
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");

                    for (int i = 0 ; i < list.length() ; i++){
                        JSONObject film = list.getJSONObject(i);
                        FilmItems filmItems = new FilmItems(film);
                        Log.d("LIST", "on success :" + filmItems.getNama());
                        Log.d("LIST", "on success :" + filmItems.getDetail());
                        filmItemses.add(filmItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return filmItemses;
    }
    protected void onReleaseResource(ArrayList<FilmItems> data){

    }

}
