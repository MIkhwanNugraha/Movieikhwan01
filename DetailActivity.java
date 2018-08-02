package com.dicoding.associate.cataloguemovie;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_TITLE          = "extra_title";
    public static String EXTRA_DETAIL2         = "extra_detail";
    public static String EXTRA_RELEASE_DATE   = "extra_release_date";
    public static String EXTRA_POSTER_JPG     = "extra_poster_jpg";


    private TextView tvNama2, tvDetail2, tvReleaseDate;
    private ImageView imgPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_detail);

        tvNama2 = (TextView)findViewById(R.id.detailNama);
        tvDetail2 = (TextView)findViewById(R.id.detailDetail2);
        tvReleaseDate = (TextView)findViewById(R.id.detailReleaseDate);
        imgPoster = (ImageView) findViewById(R.id.imgPoster);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_DETAIL2);
        String poster_jpg = getIntent().getStringExtra(EXTRA_POSTER_JPG);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);
        }catch (ParseException e){
            e.printStackTrace();
        }
        tvNama2.setText(title);
        tvDetail2.setText(overview);

        Glide
                .with(context)
                .load("http://image.tmdb.org/t/p/w185/"+poster_jpg)
                .into(imgPoster);
    }
}
