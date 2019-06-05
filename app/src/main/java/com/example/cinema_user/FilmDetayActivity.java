package com.example.cinema_user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilmDetayActivity extends AppCompatActivity {
    ImageView filmDetayImageView;
    TextView filmDetayFilmIsim, filmDetayTur, filmDetayYil, filmDetayAciklama, filmDetayYonetmen, filmDetayOyuncular;
    RecyclerView filmDetayReclyerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    //koltuk numaralarını tutması için liste lazım
    List<String> koltukList;
    KoltukAdapter koltukAdapter;
    String filmadi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detay);
        tanimla();
        getDetay();
    }

    public void tanimla() {
        Bundle bundle = getIntent().getExtras();
        filmadi=bundle.getString("filmadi");

        filmDetayImageView = findViewById(R.id.filmDetayImageView);
        filmDetayFilmIsim = findViewById(R.id.filmDetayFilmIsim);
        filmDetayTur = findViewById(R.id.filmDetayTur);
        filmDetayYil = findViewById(R.id.filmDetayYil);
        filmDetayAciklama = findViewById(R.id.filmDetayAciklama);
        filmDetayYonetmen = findViewById(R.id.filmDetayYonetmen);
        filmDetayOyuncular = findViewById(R.id.filmDetayOyuncular);
        filmDetayReclyerView = findViewById(R.id.filmDetayReclyerView);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

        koltukList = new ArrayList<>();
        filmDetayReclyerView.setLayoutManager(new GridLayoutManager(FilmDetayActivity.this, 4));
        koltukAdapter = new KoltukAdapter(koltukList, FilmDetayActivity.this, FilmDetayActivity.this,filmadi);
        filmDetayReclyerView.setAdapter(koltukAdapter);

    }

    public void getDetay() {
        Bundle bundle = getIntent().getExtras();
        filmDetayFilmIsim.setText(bundle.getString("filmadi"));
        filmDetayTur.setText("film türü : " + bundle.getString("tur"));
        filmDetayYil.setText("Yıl : " + bundle.getString("yil"));
        filmDetayAciklama.setText("Açıklama : " + bundle.getString("aciklama"));
        filmDetayYonetmen.setText("Yonetmen : " + bundle.getString("yonetmen"));
        filmDetayOyuncular.setText("Oyuncular : " + bundle.getString("oyuncular"));

        Picasso.get().load(bundle.getString("resim")).resize(1200, 1500).into(filmDetayImageView);

        koltuk(bundle.getString("filmadi"));
    }

    public void koltuk(String filmadi) {
        //child filmadi na bak nasıl çalıştığına burada?
        reference.child("Koltuklar").child(filmadi).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                koltukList.add(dataSnapshot.getKey());
                koltukAdapter.notifyDataSetChanged();
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
}
