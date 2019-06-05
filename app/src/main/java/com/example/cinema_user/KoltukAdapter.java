package com.example.cinema_user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KoltukAdapter extends RecyclerView.Adapter<KoltukAdapter.ViewHolder> {

    List<String> koltukList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String filmadi;

    public KoltukAdapter(List<String> koltukList, Activity activity, Context context, String filmadi) {
        this.koltukList = koltukList;
        this.activity = activity;
        this.context = context;
        this.filmadi = filmadi;
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.koltuklayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        //setlemeler burada yapılır
        holder.koltukTextView.setText(koltukList.get(position));
        //add childevent listener ne işe yarıyor araştır bu problem için?
        reference.child("Koltuklar").child(filmadi).child(koltukList.get(position)).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String kisiIsmi = dataSnapshot.getValue().toString();

                holder.koltukImageView.setImageResource(R.drawable.koltukbos);

                if (kisiIsmi.equals("")) {
                    holder.koltukImageView.setImageResource(R.drawable.koltukbos);
                } else {
                    holder.koltukImageView.setImageResource(R.drawable.koltukdolu);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

    @Override
    public int getItemCount() {
        return koltukList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //kullanacagımız layout taki tanımlamaları burada tanımlarız
        ImageView koltukImageView;
        TextView koltukTextView;
        LinearLayout koltukLinearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            //tanımladıklarımız itemlara atama işlemlerini ise burada yapıyoruz
            koltukImageView = itemView.findViewById(R.id.koltukImageView);
            koltukTextView = itemView.findViewById(R.id.koltukTextView);
            koltukLinearLayout = itemView.findViewById(R.id.koltukLinearLayout);
        }
    }
}
