package com.example.ricerkit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.View;


import com.example.ricerkit.Utility.NetworkChangeListener;
import com.example.ricerkit.adapters.ChildRecyclerViewAdapter;
import com.example.ricerkit.adapters.CommentRecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NetworkChangeListener networkChangeListener =  new NetworkChangeListener();
    public static FavoriteDatabase favoriteDatabase;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter, newadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoriteDatabase= Room.databaseBuilder(getApplicationContext(),FavoriteDatabase.class,"myfavdb").allowMainThreadQueries().build();

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        List<FavoriteList> favoriteLists = MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();
        adapter = new FavoriteAdapter(favoriteLists,MainActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


    public void openKdestore (View view) {
        Intent intent = new Intent(MainActivity.this, storekde.class);
        startActivity(intent);
    }
    public void openxfcestore (View view) {
        Intent intent = new Intent(MainActivity.this, storexfce.class);
        startActivity(intent);
    }
    public void opengnomestore (View view) {
        Intent intent = new Intent(MainActivity.this, storegnome.class);
        startActivity(intent);
    }
    public void opencinnamomstore (View view) {
        Intent intent = new Intent(MainActivity.this, storecinnamom.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    protected void onResume() {
        List<FavoriteList> favoriteLists = MainActivity.favoriteDatabase.favoriteDao().getFavoriteData();
        newadapter = new FavoriteAdapter(favoriteLists,MainActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(newadapter);
        newadapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}