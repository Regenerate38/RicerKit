package com.example.ricerkit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.Models.ParentModel;
import com.example.ricerkit.Models.SearchModel;
import com.example.ricerkit.Utility.NetworkChangeListener;
import com.example.ricerkit.adapters.ChildRecyclerViewAdapter;
import com.example.ricerkit.adapters.SearchResultRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SearchResult extends AppCompatActivity {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    private RecyclerView SearchResultRecyclerView;


    TextView searchWord, noresult;
    ImageButton btnsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        SearchResultRecyclerView = findViewById(R.id.searchresult_recyclerView);
        searchWord = findViewById(R.id.text111);
        noresult = findViewById(R.id.noresult);
        noresult.setVisibility(INVISIBLE);

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        String search_query = getIntent().getStringExtra("Search Query");
        String search_word = getIntent().getStringExtra("Search Word");
        searchWord.setText(search_word);
        myThread(search_query);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    private void myThread(String search_query ){
        new Thread(new Runnable(){
            @Override
            public void run() {
               // String appcategory, appauthor, appdescription, appImage;

                try{
                    ArrayList<SearchModel> array = new ArrayList<>();

//                    Create a Document object to link to the target website to obtain
                    Document doc = Jsoup.connect(search_query).get();
                    //Create an Elements object to get all the contents of div.slist


                    Elements productlist = doc.select("div.product-list > div.explore-product > div.explore-product-imgcolumn > figure > a > div.text-center.imageContainer_");
                    Elements productlistfortitle = doc.select("div.product-list > div.explore-product > div.explore-product-details > h3");
                    Elements productlistforcategory = doc.select("div.product-list > div.explore-product > div.explore-product-details");
                  Log.d("TAG", productlist.toString());
//                    Traverse the content of the Elements object
                    for (int j = 0 ;j<10;j++) {

                        String title = productlistfortitle.get(j).select("a").text();//Get the title content
                        String imageUrl = productlist.get(j).select("img").attr("src");//Get image link
                        String appurl = "http://www.pling.com" + productlistfortitle.get(j).select("a").attr("href");
                        String author = productlistforcategory.get(j).select("div:nth-child(2) > b:nth-child(2) > a:nth-child(1)").text();
                        String category = productlistforcategory.get(j).select("div:nth-child(3) > div:nth-child(2) > b:nth-child(1)").text();
                        Log.e("CATEGORYTAG", author);
                        array.add(new SearchModel(imageUrl, title, appurl, author, category));


                    }
                        Handler uiHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (array != null && array.size()>0) {

                                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchResult.this, LinearLayoutManager.VERTICAL, false);
                                    SearchResultRecyclerView.setLayoutManager(layoutManager);
                                    SearchResultRecyclerView.setHasFixedSize(true);

                                    SearchResultRecyclerViewAdapter searchResultAdapter = new SearchResultRecyclerViewAdapter(array, SearchResultRecyclerView.getContext());
                                    SearchResultRecyclerView.setAdapter(searchResultAdapter);

                                }
                                else {

                                    Log.e("TAG", "The Array is empty");
                                    SearchResultRecyclerView.setVisibility(INVISIBLE);
                                    noresult.setVisibility(VISIBLE);
                                }
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

