package com.example.ricerkit;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.Models.CommentModel;
import com.example.ricerkit.Models.GalleryModel;
import com.example.ricerkit.Models.ParentModel;
import com.example.ricerkit.Utility.ExpandableTextView;
import com.example.ricerkit.Utility.NetworkChangeListener;
import com.example.ricerkit.adapters.CatOwlAdapter;
import com.example.ricerkit.adapters.ChildRecyclerViewAdapter;
import com.example.ricerkit.adapters.CommentRecyclerViewAdapter;
import com.example.ricerkit.adapters.GalleryRecyclerViewAdapter;
import com.example.ricerkit.adapters.XfceRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppContentsActivity extends AppCompatActivity implements View.OnClickListener {

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ImageView applogo, appImagebox;
    Button favbtn;
    TextView appName, appCat, appliUrl, appdev, apprating;
    ExpandableTextView appdesc;
    private RecyclerView SimilarAppsSlider, GallerySlider, CommentSlider;
    private RecyclerView.Adapter SimilarAppsAdapter, GalleryRecyclerViewAdapter, CommentRecyclerViewAdapter;
    private RecyclerView.LayoutManager SimilarAppsLayoutManager, GalleryLayoutManager, CommentLayoutManager;
    String jsonimg;
    String appimgurl;
    ProgressBar progressBar;
    LinearLayout mainll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_content);

        progressBar = findViewById(R.id.progressBar2);
        mainll1 = findViewById(R.id.mainll1);
        appdev =findViewById(R.id.tv_app_developer);
        applogo = findViewById(R.id.iv_app_image);
        appName = findViewById(R.id.tv_app_name);
        appCat = findViewById(R.id.tv_app_type);
        apprating = findViewById(R.id.tv_app_rating);
        appliUrl = findViewById(R.id.randomtxtbox);
        appdesc = findViewById(R.id.tv_app_description);
        favbtn = findViewById(R.id.btn_install);

        progressBar.setVisibility(View.VISIBLE);
        mainll1.setVisibility(View.INVISIBLE);
        String appname = getIntent().getStringExtra("Name");
         appimgurl = getIntent().getStringExtra("ImageUrl");
        String appliurl = getIntent().getStringExtra("MainUrl");


        String appsubtract = "http://www.pling.com/p/";
        String res = appliurl.replace(appsubtract, "");
        String last = res.replace("/", "");
        int iid = Integer.parseInt(last);
        Log.e("TAAAAAAAg", last);

        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(iid)==1) {
            Log.e("TAG", "The item already exists");
            favbtn.setText("Remove From Library");
        }
        else {
            Log.e("taag", "The item doesn't exist.");
            favbtn.setText("Add to Favorites");
        }
        appName.setText(appname);
        appliUrl.setText(appliurl);
        Picasso.get().load(appimgurl).into(applogo);

        myThread(appliurl);
        favbtn.setOnClickListener(this);


    }


    @Override
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

    private void myThread(String appliurl){
        new Thread(new Runnable(){
            @Override
            public void run() {
                 String appcategory, appauthor, appdescription;

                try{
                    ArrayList<ChildModel> array = new ArrayList<>();
                    ArrayList<GalleryModel> galleryarray = new ArrayList<>();
                    ArrayList<CommentModel> commentArray = new ArrayList<>();
//                    Create a Document object to link to the target website to obtain
                    Document doc = Jsoup.connect(appliurl).get();
                    //Create an Elements object to get all the contents of div.slist

                    Elements productauthor = doc.select("div.product-maker-summary > h5");
                    Elements productcategory = doc.select("div#product-header-info-container > p.product_category");
                    Elements productdescription = doc.select("div#product-about");
                    Elements similarproductsauthor = doc.select("div.prod-widget-box.right.moreproducts:first-child > div.sidebar-content > div.sidebar-content-section > div.row.product-row > div.product-thumbnail > a");
                    Elements productrating = doc.select("div.kkSWyw");
                    Elements productcommentslist = doc.select("div.comments-list");
                    Elements productcomments = doc.select("div.comments-list > div.media");

//


// the script elements have no identifying charateristic so we must loop
// until we find the one which contains the "infosite.token" variable



//                    Traverse the content of the Elements object

                        appcategory = productcategory.select("a").get(0).text();//Get the title content
                        appauthor = productauthor.select("a").text();//Get image link
                        appdescription = productdescription.select("article").html();
                        String temp = appdescription.replace ("<br>", "$$$");
                        Document doc1 = Jsoup.parse(temp);
                        String appdescriptionfinal = doc1.body().text().replace("$$$","\n");
                        String appsrating = productrating.text();

                        Log.e("TAg", appcategory);
                    Log.e("TAg", appcategory);
                    Log.e("TAg", appauthor);
                    Log.e("TAg", appsrating);

                    Log.e("TAg", appdescriptionfinal);
            try {
                for (int j = 0; j < productcomments.size(); j++) {
                    String commentUserImage = productcomments.get(j).select("a.media-left > div.profileimage > img").attr("src");
                    String commentUserName = productcomments.get(j).select("div.media-body > h4 > a").text();
                    String commentUploadTime = productcomments.get(j).select("div.media-body > h4 > p.pull-right").text();
                   String commentContent = productcomments.get(j).select("div.media-body > div.text").text();
//                        String temp2 = commentContent.replace ("<br>", "$$$");
//                        Document doc2 = Jsoup.parse(temp2);
//                        String commentContentfinal = doc2.body().text().replace("$$$","\n");
                   commentArray.add(new CommentModel(commentUserImage, commentUserName,commentUploadTime, commentContent));
                }


                Handler uiihandler = new Handler((Looper.getMainLooper()));
                uiihandler.post(new Runnable() {
                    @Override
                    public void run() {
                        CommentSlider =  findViewById(R.id.Comments);
                        CommentSlider.setHasFixedSize(true);
                        CommentLayoutManager = new LinearLayoutManager(AppContentsActivity.this, LinearLayoutManager.VERTICAL, false);
                        CommentRecyclerViewAdapter = new CommentRecyclerViewAdapter(commentArray ,AppContentsActivity.this);
                        CommentSlider.setLayoutManager(CommentLayoutManager);
                        CommentSlider.setAdapter(CommentRecyclerViewAdapter);
                        CommentRecyclerViewAdapter.notifyDataSetChanged();

                    }
                });



            }
                 catch   (Exception e){
                        Log.d("TAAAAG",e.toString());
                    }


            try {
                for (int j = 0; j <= similarproductsauthor.size(); j++) {
                    String imageUrl = similarproductsauthor.get(j).select("img").attr("src");//Get image link
                    String appurl = "http://www.pling.com" + similarproductsauthor.get(j).attr("href");
                    String title = similarproductsauthor.get(j).attr("title");
                    array.add(new ChildModel(imageUrl, title, appurl));
                }

                }
                catch   (Exception e){
                    Log.d("TAAAAG",e.toString());
                }

                        Handler uiHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                            appCat.setText(appcategory);
                            appdev.setText(appauthor);
                            appdesc.setText(appdescriptionfinal);
                            apprating.setText(appsrating);

//                            Picasso.get().load(appImage).into(appImagebox);
                                SimilarAppsSlider =  findViewById(R.id.SimilarApps);
                                SimilarAppsSlider.setHasFixedSize(true);
                                SimilarAppsLayoutManager = new LinearLayoutManager(AppContentsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                SimilarAppsAdapter = new ChildRecyclerViewAdapter(array,AppContentsActivity.this);
                                SimilarAppsSlider.setLayoutManager(SimilarAppsLayoutManager);
                                SimilarAppsSlider.setAdapter(SimilarAppsAdapter);
                                SimilarAppsAdapter.notifyDataSetChanged();

                                appdev.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(AppContentsActivity.this, ProfileActivity.class);
                                        Bundle extras = new Bundle();
                                        extras.putString("Author Name", appauthor);
                                        extras.putString("Author Url", "http://www.pling.com/u/" + appauthor);
                                        intent.putExtras(extras);
                                        startActivity(intent);
                                    }
                                });


                            }

                        });

                    Elements scriptElements = doc.getElementsByTag("script");
                try {
                    for (Element element : scriptElements) {
                        if (element.data().contains("galleryPicturesJson")) {
                            // find the line which contains 'infosite.token = <...>;'
                            Pattern pattern = Pattern.compile("var galleryPicturesJson = (.*);");
                            Matcher matcher = pattern.matcher(element.data());
                            // we only expect a single match here so there's no need to loop through the matcher's groups
                            if (matcher.find()) {
                                jsonimg = matcher.group(1);

                            } else {
                                System.err.println("No match found!");
                            }
                            break;
                        }
                    }

                }
                catch   (Exception e){
                    Log.d("TAAAAG",e.toString());
                }
                            JSONArray jsonArray = new JSONArray(jsonimg);

                try {
                    if (jsonArray != null ) {
                        for (int j = 0; j < jsonArray.length(); j++) {

                            String str_value = jsonArray.optString(j);
                            Log.e("TAAAG", str_value);
                            galleryarray.add(new GalleryModel(str_value));
                        }
                    }
                    else {}

                }
                catch   (Exception e){
                    Log.d("TAAAAG",e.toString());
                }

                        Handler uixHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                GallerySlider = findViewById(R.id.GallerySlider);
                                GallerySlider.setHasFixedSize(true);
                                GalleryLayoutManager = new LinearLayoutManager(AppContentsActivity.this, LinearLayoutManager.HORIZONTAL, false);
                                GalleryRecyclerViewAdapter = new GalleryRecyclerViewAdapter(galleryarray, AppContentsActivity.this);
                                GallerySlider.setLayoutManager(GalleryLayoutManager);
                                GallerySlider.setAdapter(GalleryRecyclerViewAdapter);
                                GalleryRecyclerViewAdapter.notifyDataSetChanged();
                            }
                        });








                }
                catch (Exception e){
                    Log.d("TAG",e.toString());
                }
                Handler uiixHandler = new Handler(Looper.getMainLooper());
                uiixHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        mainll1.setVisibility(View.VISIBLE);
                    }
                });


            }
        }).start();


    }


    @Override
    public void onClick(View v) {
        FavoriteList favoriteList=new FavoriteList();


        String image = appimgurl;
        String name = appName.getText().toString();
        String appurl = appliUrl.getText().toString();
        String appsubtract = "http://www.pling.com/p/";
        String res = appurl.replace(appsubtract, "");
        String last = res.replace("/", "");
        int id = Integer.parseInt(last);
        Log.e("TAAAAAAAg", last);
        favoriteList.setId(id);
        favoriteList.setImage(image);
        favoriteList.setName(name);;

        if (MainActivity.favoriteDatabase.favoriteDao().isFavorite(id)!=1) {
            MainActivity.favoriteDatabase.favoriteDao().addData(favoriteList);
            favbtn.setText("Remove from LIbrary");
        }
        else {
            MainActivity.favoriteDatabase.favoriteDao().delete(favoriteList);
            favbtn.setText("Add to Library");
        }
    }
}

