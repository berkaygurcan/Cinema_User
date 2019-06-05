package com.example.cinema_user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {

    List<FilmModel> filmModelList;
    Activity activity;
    Context context;

    public FilmAdapter(List<FilmModel> filmModelList, Activity activity, Context context) {
        this.filmModelList = filmModelList;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filmlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //setlemeler burada yapılır
        holder.filmIsim.setText(filmModelList.get(position).getFilmismi().toString());
        Picasso.get().load(filmModelList.get(position).getResim()).resize(800, 1000).into(holder.filmResim);

        holder.anaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FilmDetayActivity.class);
                intent.putExtra("filmadi", filmModelList.get(position).getFilmismi());
                intent.putExtra("aciklama", filmModelList.get(position).getAciklama());
                intent.putExtra("oyuncular", filmModelList.get(position).getOyuncular());
                intent.putExtra("tur", filmModelList.get(position).getTur());
                intent.putExtra("yil", filmModelList.get(position).getYil());
                intent.putExtra("yonetmen", filmModelList.get(position).getYonetmen());
                intent.putExtra("resim", filmModelList.get(position).getResim());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //kullanacagımız layout taki tanımlamaları burada tanımlarız
        ImageView filmResim;
        TextView filmIsim;
        LinearLayout anaLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            //tanımladıklarımız itemlara atama işlemlerini ise burada yapıyoruz
            filmResim = itemView.findViewById(R.id.filmResim);
            filmIsim = itemView.findViewById(R.id.filmIsim);
            anaLinearLayout = itemView.findViewById(R.id.anaLinearLayout);
        }
    }
}
