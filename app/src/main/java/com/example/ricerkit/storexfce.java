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
import com.example.ricerkit.adapters.ParentRecyclerViewAdapter;
import com.example.ricerkit.adapters.XfceRecyclerViewAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

public class storexfce extends AppCompatActivity implements  MaterialSearchBar.OnSearchActionListener {

    private RecyclerView parentRecyclerView;
    MaterialSearchBar searchBar;
    private RecyclerView categoriesSlider;
    private RecyclerView.Adapter ParentAdapter, catcarouselAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager, catcarouselLayoutManager;

    NetworkChangeListener networkChangeListener =  new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storexfce);

        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);

        //set the Categories for each array list set in the `ParentViewHolder`
        parentModelArrayList.add(new ParentModel("XFCE Latest"));
        parentModelArrayList.add(new ParentModel("XFCE Highest Rating"));
        parentModelArrayList.add(new ParentModel("Xfce Themes"));
        parentModelArrayList.add(new ParentModel("XFCE Wallpapers"));
        parentModelArrayList.add(new ParentModel("Compiz Themes"));
        parentModelArrayList.add(new ParentModel("GTK2 Themes"));
        parentModelArrayList.add(new ParentModel("GTK3/4 Themes"));
        parentModelArrayList.add(new ParentModel("Beryl Themes"));
        parentModelArrayList.add(new ParentModel("XFCE Screenshots"));
        parentModelArrayList.add(new ParentModel("Cursors"));




        parentRecyclerView = findViewById(R.id.Parent_recyclerView);

        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(this);
        ParentAdapter = new XfceRecyclerViewAdapter(parentModelArrayList,storexfce.this);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();

        ArrayList<CatOwl> arrayList = new ArrayList<>();
// added the first child row
        arrayList.add(new CatOwl("Beryl/Emerald Themes","http://store.kde.org/browse/cat/117/order/latest"));
        arrayList.add(new CatOwl("XFCE/XFWM4 Themes", "http://store.kde.org/browse/cat/138/order/latest"));
        arrayList.add(new CatOwl("Cursors","http://store.kde.org/browse/cat/107/order/latest"));
        arrayList.add(new CatOwl("Compiz Themes","http://store.kde.org/browse/cat/116/order/latest"));
        arrayList.add(new CatOwl("GTK 2 Themes", "http://store.kde.org/browse/cat/136/order/latest"));
        arrayList.add(new CatOwl("GTK 3/4 Themes", "http://store.kde.org/browse/cat/135/order/latest"));
        arrayList.add(new CatOwl("XFCE Wallpapers","http://store.kde.org/browse/cat/302/order/latest"));
        arrayList.add(new CatOwl("XFCE Screenshots","http://store.kde.org/browse/cat/256/order/latest"));

        categoriesSlider =  findViewById(R.id.catcarousel);
        categoriesSlider.setHasFixedSize(true);
        catcarouselLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        catcarouselAdapter = new CatOwlAdapter(arrayList,storexfce.this);
        categoriesSlider.setLayoutManager(catcarouselLayoutManager);
        categoriesSlider.setAdapter(catcarouselAdapter);
        catcarouselAdapter.notifyDataSetChanged();

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
        Intent intent = new Intent(storexfce.this, SearchResult.class);
        String finsearchquery = "https://www.cinnamon-look.org/find?search=" + searchBar.getText().toString();
        intent.putExtra("Search Query", finsearchquery);
        startActivity(intent);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }


}