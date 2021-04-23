package com.example.ricerkit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.Utility.NetworkChangeListener;
import com.example.ricerkit.adapters.ChildRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    NetworkChangeListener networkChangeListener =  new NetworkChangeListener();
    TextView deveusername, shortdesc, statsbox;
    ImageView deveimg;
    private RecyclerView SameDevSlider;
    private RecyclerView.Adapter SameDevAdapter;
    private RecyclerView.LayoutManager SameDevLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String appname = getIntent().getStringExtra("Author Name");
        String appimgurl = getIntent().getStringExtra("Author Url");

        shortdesc = findViewById(R.id.shortdesc);
        deveusername = findViewById(R.id.deveusername);
        deveimg = findViewById(R.id.profileimg);
        deveusername.setText(appname);
//        statsbox = findViewById(R.id.stats);

        myThread(appimgurl);
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
    private void myThread(String appimgurl){
        new Thread(new Runnable(){
            @Override
            public void run() {
                String devestats, deveimage, appdescription;

                try{
                    ArrayList<ChildModel> array = new ArrayList<>();
//                    ArrayList<ChildModel> simiilarbycategory = new ArrayList<>();

//                    Create a Document object to link to the target website to obtain
                    Document doc = Jsoup.connect(appimgurl).get();
                    //Create an Elements object to get all the contents of div.slist

                    Elements userimage = doc.select("div > article > div.about-title > figure");
                    Elements productdescription = doc.select("div > article > div.about-footer > div > div.details.stat > div");
                    Elements productshortdesc = doc.select("div > article > div.about-footer > div > div.details.info");
                    Elements productbyauthor = doc.select("div.product.mini-card");
                    Elements productbyauthorlink =  doc.select("div.product.mini-card > div.u-wrap");
                    Elements productbyauthorimg = doc.select("div.product.mini-card > div.u-wrap > a > figure");
                    Elements productbyauthortitle = doc.select("div.product.mini-card > div.u-wrap > a > div.u-content > h3");

                    for (int j = 0; j < productbyauthor.size(); j++) {
                        String imageUrl = productbyauthorimg.get(j).select("img").attr("src");//Get image link
                        String appurl = "http://www.pling.com" + productbyauthorlink.get(j).select("a").attr("href");
                        String title = productbyauthor.get(j).text();

                        array.add(new ChildModel(imageUrl, title, appurl));
                    }
                    
//                    Traverse the content of the Elements object


                    deveimage = userimage.select("img").attr("src");//Get image link

                    for (int j = 0; j < productdescription.size(); j++) {
                        Log.e("ROW TAG", productdescription.get(j).select(".row").text());
                    }
                    devestats = productdescription.text();
                    appdescription = productshortdesc.text();



                    Log.e("TAG", "Description"+ appdescription);




                    Handler uiHandler = new Handler(Looper.getMainLooper());
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.get().load(deveimage).into(deveimg);
                           shortdesc.setText(appdescription);
                          //  statsbox.setText(devestats);
//

                            SameDevSlider =  findViewById(R.id.SameDevApps);
                            SameDevSlider.setHasFixedSize(true);
                            SameDevLayoutManager = new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            SameDevAdapter = new ChildRecyclerViewAdapter(array,ProfileActivity.this);
                            SameDevSlider.setLayoutManager(SameDevLayoutManager);
                            SameDevSlider.setAdapter(SameDevAdapter);
                            SameDevAdapter.notifyDataSetChanged();
                            
                            

                        }

                    });





                }
                catch (Exception e){
                    Log.d("TAG",e.toString());
                }

            }
        }).start();
    }
}