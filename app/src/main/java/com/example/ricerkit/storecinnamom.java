package com.example.ricerkit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ricerkit.Models.CatOwl;
import com.example.ricerkit.Models.ParentModel;
import com.example.ricerkit.Utility.NetworkChangeListener;
import com.example.ricerkit.adapters.CatOwlAdapter;
import com.example.ricerkit.adapters.CinnamomRecyclerViewAdapter;
import com.example.ricerkit.adapters.XfceRecyclerViewAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class storecinnamom extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {

        MaterialSearchBar searchBar;

        EditText searchfield;
        ImageButton btnsearch;
private RecyclerView parentRecyclerView;
private RecyclerView categoriesSlider;
private RecyclerView.Adapter ParentAdapter, catcarouselAdapter;
        ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
private RecyclerView.LayoutManager parentLayoutManager, catcarouselLayoutManager;
private List<String> lastSearches;

        NetworkChangeListener networkChangeListener =  new NetworkChangeListener();

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storecinnamom);
        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);


        //set the Categories for each array list set in the `ParentViewHolder`
        parentModelArrayList.add(new
        ParentModel("Cinnamom Latest"));
        parentModelArrayList.add(new
        ParentModel("Cinnamom Highest Rating"));
        parentModelArrayList.add(new
        ParentModel("Cinnamom Extensions"));
        parentModelArrayList.add(new
        ParentModel("Cinnamom Screenshots"));
        parentModelArrayList.add(new
        ParentModel("Cinnamom Themes"));
        parentModelArrayList.add(new
                ParentModel("MDM Themes"));
        parentModelArrayList.add(new
                ParentModel("Wallpaper Mint"));
        parentModelArrayList.add(new
                ParentModel("Cinnamom Desklets"));
        parentModelArrayList.add(new
                ParentModel("Cinnamom Applets"));
        parentModelArrayList.add(new
                ParentModel("GTK 3/4 Themes"));
        parentModelArrayList.add(new
                ParentModel("Cursors"));
        parentRecyclerView =
        findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(this);
        ParentAdapter = new CinnamomRecyclerViewAdapter(parentModelArrayList,storecinnamom.this);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();

        ArrayList<CatOwl> arrayList = new ArrayList<>();
// added the first child row
        arrayList.add(new CatOwl("Cinnamom Screenshots", "http://store.kde.org/browse/cat/227/order/latest"));
        arrayList.add(new CatOwl("Cursors","http://store.kde.org/browse/cat/107/order/latest"));
        arrayList.add(new CatOwl("GTK 3/4 Themes", "http://store.kde.org/browse/cat/135/order/latest"));
        arrayList.add(new CatOwl("MDM Themes", "http://store.kde.org/browse/cat/153/order/latest"));
        arrayList.add(new CatOwl("Wallpaper Mint","http://store.kde.org/browse/cat/283/order/latest"));
        arrayList.add(new CatOwl("Cinnamom Applets","http://store.kde.org/browse/cat/264/order/latest"));
        arrayList.add(new CatOwl("Cinnamom Desklets","http://store.kde.org/browse/cat/57/order/latest"));
        arrayList.add(new CatOwl("Cinnamom Themes","http://store.kde.org/browse/cat/133/order/latest"));
        arrayList.add(new CatOwl("Cinnamom Extensions","http://store.kde.org/browse/cat/265/order/latest"));

        categoriesSlider =  findViewById(R.id.catcarousel);
        categoriesSlider.setHasFixedSize(true);
        catcarouselLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        catcarouselAdapter = new CatOwlAdapter(arrayList,storecinnamom.this);
        categoriesSlider.setLayoutManager(catcarouselLayoutManager);
        categoriesSlider.setAdapter(catcarouselAdapter);
        catcarouselAdapter.notifyDataSetChanged();
//        btnsearch.setOnClickListener(this);
        }

protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
        }

@Override
protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
        }

        @Override
        public void onSearchStateChanged(boolean enabled) {

        }

        @Override
        public void onSearchConfirmed(CharSequence text) {
                Intent intent = new Intent(storecinnamom.this, SearchResult.class);
                String finsearchquery = "https://www.cinnamon-look.org/find?search=" + searchBar.getText().toString();
                intent.putExtra("Search Query", finsearchquery);
                startActivity(intent);
        }

        @Override
        public void onButtonClicked(int buttonCode) {

        }

//@Override
//public void onClick(View v) {
//        Intent intent = new Intent(storecinnamom.this, SearchResult.class);
//        String finsearchquery = "https://www.cinnamon-look.org/find?search=" + searchfield.getText().toString();
//        intent.putExtra("Search Query", finsearchquery);
//        startActivity(intent);
//        }
}