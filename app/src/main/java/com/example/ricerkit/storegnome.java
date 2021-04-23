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
import com.example.ricerkit.adapters.GnomeRecyclerViewAdapter;
import com.example.ricerkit.adapters.XfceRecyclerViewAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

public class storegnome extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener {


    MaterialSearchBar searchBar;
    EditText searchfield;
    ImageButton btnsearch;
    private RecyclerView parentRecyclerView;
    private RecyclerView categoriesSlider;
    private RecyclerView.Adapter ParentAdapter, catcarouselAdapter;
    ArrayList<ParentModel> parentModelArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager parentLayoutManager, catcarouselLayoutManager;

    NetworkChangeListener networkChangeListener =  new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storegnome);

        searchBar = findViewById(R.id.searchBar1);


        //set the Categories for each array list set in the `ParentViewHolder`
        parentModelArrayList.add(new ParentModel("GNOME Latest"));
        parentModelArrayList.add(new ParentModel("GNOME Highest Rating"));
        parentModelArrayList.add(new ParentModel("GNOME Extensions"));
        parentModelArrayList.add(new ParentModel("GNOME 2 Splash Screens"));
        parentModelArrayList.add(new ParentModel("GTK2 Themes"));
        parentModelArrayList.add(new ParentModel("GNOME Shell Themes"));
        parentModelArrayList.add(new ParentModel("GTK 3/4 Themes"));
        parentModelArrayList.add(new ParentModel("GDM Themes"));
        parentModelArrayList.add(new ParentModel("Cairo Clock"));
        parentModelArrayList.add(new ParentModel("GNOME 2 Color Schemes"));
        parentModelArrayList.add(new ParentModel("GNOME Wallpapers"));
        parentModelArrayList.add(new ParentModel("GnoMenu Skins"));
        parentModelArrayList.add(new ParentModel("Docks"));
        parentModelArrayList.add(new ParentModel("GNOME App Addons"));
        parentModelArrayList.add(new ParentModel("Cursors"));
        parentModelArrayList.add(new ParentModel("Various GNOME Theming"));
        parentModelArrayList.add(new ParentModel("GIMP"));
        parentModelArrayList.add(new ParentModel("Metacity Themes"));
        parentModelArrayList.add(new ParentModel("Grub Themes"));



        parentRecyclerView = findViewById(R.id.Parent_recyclerView);
        parentRecyclerView.setHasFixedSize(true);
        parentLayoutManager = new LinearLayoutManager(this);
        ParentAdapter = new GnomeRecyclerViewAdapter(parentModelArrayList,storegnome.this);
        parentRecyclerView.setLayoutManager(parentLayoutManager);
        parentRecyclerView.setAdapter(ParentAdapter);
        ParentAdapter.notifyDataSetChanged();

        ArrayList<CatOwl> arrayList = new ArrayList<>();
// added the first child row
        arrayList.add(new CatOwl("Cursors","http://store.kde.org/browse/cat/107/order/latest"));
        arrayList.add(new CatOwl("GTK 2 Themes", "http://store.kde.org/browse/cat/136/order/latest"));
        arrayList.add(new CatOwl("GTK 3/4 Themes", "http://store.kde.org/browse/cat/135/order/latest"));
        arrayList.add(new CatOwl("GNOME 2 SplashScreen", "http://store.kde.org/browse/cat/130/order/latest"));
        arrayList.add(new CatOwl("Various GNOME Stuff", "http://store.kde.org/browse/cat/207/order/latest"));
        arrayList.add(new CatOwl("GNOME Shell Themes","http://store.kde.org/browse/cat/134/order/latest"));
        arrayList.add(new CatOwl("GNOME Extensions","http://store.kde.org/browse/cat/156/order/latest"));
        arrayList.add(new CatOwl("Docks","http://store.kde.org/browse/cat/277/order/latest"));
        arrayList.add(new CatOwl("Cairo Clock","http://store.kde.org/browse/cat/208/order/latest"));
        arrayList.add(new CatOwl("GDM Themes","http://store.kde.org/browse/cat/131/order/latest"));
        arrayList.add(new CatOwl("GnoMenu Skins","http://store.kde.org/browse/cat/127/order/latest"));
        arrayList.add(new CatOwl("GNOME App Addons","http://store.kde.org/browse/cat/382/order/latest"));
        arrayList.add(new CatOwl("GNOME 2 Color Schemes","http://store.kde.org/browse/cat/200/order/latest"));
        arrayList.add(new CatOwl("Grub Themes","http://store.kde.org/browse/cat/109/order/latest"));
        arrayList.add(new CatOwl("GNOME Wallpapers","http://store.kde.org/browse/cat/300/order/latest"));
        arrayList.add(new CatOwl("Various GNOME Theming","http://store.kde.org/browse/cat/566/order/latest"));
        arrayList.add(new CatOwl("GIMP","http://store.kde.org/browse/cat/190/order/latest"));
        arrayList.add(new CatOwl("Metacity Themes","http://store.kde.org/browse/cat/125/order/latest"));



        categoriesSlider =  findViewById(R.id.catcarousel);
        categoriesSlider.setHasFixedSize(true);
        catcarouselLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        catcarouselAdapter = new CatOwlAdapter(arrayList,storegnome.this);
        categoriesSlider.setLayoutManager(catcarouselLayoutManager);
        categoriesSlider.setAdapter(catcarouselAdapter);
        catcarouselAdapter.notifyDataSetChanged();
        searchBar.setOnSearchActionListener(this);

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
        Intent intent = new Intent(storegnome.this, SearchResult.class);
        String finsearchquery = "https://www.cinnamon-look.org/find?search=" + searchBar.getText().toString();
        intent.putExtra("Search Query", finsearchquery);
        startActivity(intent);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

}