package com.dicoding.associate.cataloguemovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class FilmAdapter extends BaseAdapter {

    private ArrayList<FilmItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;


    public FilmAdapter(Context context){
        this.context = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<FilmItems> items){
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(final FilmItems item){
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        mData.clear();
    }

    @Override
    public int getItemViewType(int position){
        return 1;
    }

    public FilmItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount(){
        if (mData == null)
            return 0;
        return mData.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.films_items, null);
            holder.textViewNamaFilm = (TextView)convertView.findViewById(R.id.textFilm);
            holder.textViewDetail = (TextView)convertView.findViewById(R.id.detailFilm);
            holder.textViewTanggal = (TextView)convertView.findViewById(R.id.dateFilm);
            holder.imgGambarPoster = (ImageView) convertView.findViewById(R.id.img_item_user);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewNamaFilm.setText(mData.get(position).getNama());
        holder.textViewDetail.setText(mData.get(position).getDetail());
        holder.textViewTanggal.setText(mData.get(position).getTanggal());

        return convertView;
    }

    private static class ViewHolder{
        TextView textViewNamaFilm;
        TextView textViewDetail;
        TextView textViewTanggal;
        ImageView imgGambarPoster;

    }
}
