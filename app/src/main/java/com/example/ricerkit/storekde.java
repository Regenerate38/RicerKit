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
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

public class storekde extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener{


    private RecyclerView parentRecyclerView;
    EditText searchfield;
    MaterialSearchBar searchBar;
    ImageButton btnsearch;
    private RecyclerView categoriesSlider;
    private RecyclerView.Adapter ParentAdapter, catcarouselAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager, catcarouselLayoutManager;

    NetworkChangeListener networkChangeListener =  new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storekde);
        searchBar = findViewById(R.id.searchBar);
        searchBar.setOnSearchActionListener(this);


        //set the Categories for each array list set in the `ParentViewHolder`
        parentModelArrayList.add(new ParentModel("Latest"));
        parentModelArrayList.add(new ParentModel("Highest Rating"));
        parentModelArrayList.add(new ParentModel("Global Theme"));
        parentModelArrayList.add(new ParentModel("Kwin"));
        parentModelArrayList.add(new ParentModel("Cursors"));
        parentModelArrayList.add(new ParentModel("Kvantum"));
        parentModelArrayList.add(new ParentModel("Plasma Themes"));
        parentModelArrayList.add(new ParentModel("SDDM Themes"));
        parentModelArrayList.add(new ParentModel("Plasma Splashscreens"));
        parentModelArrayList.add(new ParentModel("Plasma Color Schemes"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Calendar"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Clocks"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Menus"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Monitoring"));
        parentModelArrayList.add(new ParentModel("Plymouth Themes"));
        parentModelArrayList.add(new ParentModel("Plasma Wallpaper Plugins"));
        parentModelArrayList.add(new ParentModel("Latte-Dock"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Weather"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Applets"));
        parentModelArrayList.add(new ParentModel("Plasma 5 Multimedia"));
        parentModelArrayList.add(new ParentModel("Plasma Window Decoration"));
        parentModelArrayList.add(new ParentModel("KDE Plasma Screenshots"));



        parentRecyclerView = findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(this);
        ParentAdapter = new ParentRecyclerViewAdapter(parentModelArrayList,storekde.this);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();



        ArrayList<CatOwl> arrayList = new ArrayList<>();
// added the first child row
        arrayList.add(new CatOwl("Plasma Themes", "http://store.kde.org/browse/cat/104/order/latest"));
        arrayList.add(new CatOwl("Plymouth Themes","http://store.kde.org/browse/cat/108/order/latest"));
        arrayList.add(new CatOwl("Plasma SplashScreen", "http://store.kde.org/browse/cat/488/order/latest"));
        arrayList.add(new CatOwl("Plasma Window Decoration", "http://store.kde.org/browse/cat/114/order/latest"));
        arrayList.add(new CatOwl("Latte Dock","http://store.kde.org/browse/cat/562/order/latest"));
        arrayList.add(new CatOwl("Kwin Scripts","http://store.kde.org/browse/cat/210/order/latest"));
        arrayList.add(new CatOwl("Kwin Effects","http://store.kde.org/browse/cat/209/order/latest"));
        arrayList.add(new CatOwl("Global Themes","http://store.kde.org/browse/cat/121/order/latest"));
        arrayList.add(new CatOwl("Plasma 5 Multimedia","http://store.kde.org/browse/cat/420/order/latest"));
        arrayList.add(new CatOwl("Plasma 5 Applets","http://store.kde.org/browse/cat/105/order/latest"));
        arrayList.add(new CatOwl("Plasma 5 Clocks","http://store.kde.org/browse/cat/399/order/latest"));
        arrayList.add(new CatOwl("Plasma 5 Monitoring","http://store.kde.org/browse/cat/425/order/latest"));
        arrayList.add(new CatOwl("Plasma 5 Menus","http://store.kde.org/browse/cat/398/order/latest"));

        categoriesSlider =  findViewById(R.id.catcarousel);
        categoriesSlider.setHasFixedSize(true);
        catcarouselLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        catcarouselAdapter = new CatOwlAdapter(arrayList,storekde.this);
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
        Intent intent = new Intent(storekde.this, SearchResult.class);
        String finsearchquery = "http://store.kde.org/find?search=" + searchBar.getText().toString();
        intent.putExtra("Search Query", finsearchquery);
        startActivity(intent);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

}