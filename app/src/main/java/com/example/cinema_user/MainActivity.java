package com.example.cinema_user;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
RecyclerView recylerview;
List<FilmModel> filmModelList;
FilmAdapter filmAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
    }
    public void tanimla()
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        filmModelList=new ArrayList<>();
        recylerview=findViewById(R.id.recylerview);
        recylerview.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        filmAdapter = new FilmAdapter(filmModelList,MainActivity.this,MainActivity.this);
        recylerview.setAdapter(filmAdapter);
        getFilms();


    }
    public void getFilms()
    {
        reference.child("Filmler").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FilmModel filmModel=dataSnapshot.getValue(FilmModel.class);
                filmModelList.add(filmModel);
                Log.i("deneme",dataSnapshot.toString());
                filmAdapter.notifyDataSetChanged();
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
