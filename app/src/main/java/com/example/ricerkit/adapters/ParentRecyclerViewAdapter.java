package com.example.ricerkit.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.Models.ParentModel;
import com.example.ricerkit.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;


public class ParentRecyclerViewAdapter extends
        RecyclerView.Adapter<ParentRecyclerViewAdapter.MyViewHolder>
{

    private String linktoparse;
    private ArrayList<ParentModel> parentModelArrayList;
    public Context cxt;
    public static class MyViewHolder extends
            RecyclerView.ViewHolder {
        public TextView category;
        public RecyclerView childRecyclerView;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.Movie_category);
            childRecyclerView = itemView.findViewById(R.id.Child_RV);
            progressBar = itemView.findViewById(R.id.progressBar2);

        }
    }
    public ParentRecyclerViewAdapter(ArrayList<ParentModel>
                                      exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.progressBar.setVisibility(View.VISIBLE);
        ParentModel currentItem =  parentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt,  LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);
        holder.category.setText(currentItem.movieCategory());

        ArrayList<ChildModel> arrayList = new ArrayList<>();
// added the first child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Latest"))
        {
//
            linktoparse = "http://store.kde.org/browse/cat";
            myThread(holder, linktoparse);
        }
// added in second child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Highest Rating"))
        {
            linktoparse = "http://store.kde.org/browse/ord/rating";
            myThread(holder, linktoparse);
        }
// added in third child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Global Theme"))
        {
            linktoparse = "http://store.kde.org/browse/cat/121/order/latest";
            myThread(holder, linktoparse);
        }
// added in fourth child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Kwin"))
        {
            linktoparse = "https://store.kde.org/browse/cat/349/order/latest";
            myThread(holder, linktoparse);
        }
// added in fifth child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Extensions"))
        {
            linktoparse = "http://store.kde.org/browse/cat/418/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Cursors"))
        {
            linktoparse = "http://store.kde.org/browse/cat/107/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Kvantum"))
        {
            linktoparse = "http://store.kde.org/browse/cat/123/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Latte-Dock"))
        {
            linktoparse = "http://store.kde.org/browse/cat/562/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("KDE Plasma Screenshots"))
        {
            linktoparse = "http://store.kde.org/browse/cat/228/order/latest";
            myThread(holder, linktoparse);
        }

        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma Color Schemes"))
        {
            linktoparse = "http://store.kde.org/browse/cat/112/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma Splashscreens"))
        {
            linktoparse = "http://store.kde.org/browse/cat/488/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma Wallpaper Plugins"))
        {
            linktoparse = "http://store.kde.org/browse/cat/419/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma Themes"))
        {
            linktoparse = "http://store.kde.org/browse/cat/104/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma Window Decoration"))
        {
            linktoparse = "http://store.kde.org/browse/cat/114/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plymouth Themes"))
        {
            linktoparse = "http://store.kde.org/browse/cat/108/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("SDDM Themes"))
        {
            linktoparse = "http://store.kde.org/browse/cat/101/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Applets"))
        {
            linktoparse = "http://store.kde.org/browse/cat/105/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Calendar"))
        {
            linktoparse = "http://store.kde.org/browse/cat/463/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Clocks"))
        {
            linktoparse = "http://store.kde.org/browse/cat/399/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Menus"))
        {
            linktoparse = "http://store.kde.org/browse/cat/398/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Monitoring"))
        {
            linktoparse = "http://store.kde.org/browse/cat/425/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Multimedia"))
        {
            linktoparse = "http://store.kde.org/browse/cat/420/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Plasma 5 Weather"))
        {
            linktoparse = "http://store.kde.org/browse/cat/424/order/latest";
            myThread(holder, linktoparse);
        }


    }
    public void myThread(MyViewHolder holder,String linktoparse){
        new Thread(){
            @Override
            public void run() {
                try{
                    ArrayList<ChildModel> array = new ArrayList<>();

//                    Create a Document object to link to the target website to obtain
                    Document doc = Jsoup.connect(linktoparse).get();
                    //Create an Elements object to get all the contents of div.slist
                    Elements productlist = doc.select("div.product-list > div.explore-product > div.explore-product-imgcolumn > figure > a > div.text-center.imageContainer_");
                    Elements productlistfortitle = doc.select("div.product-list > div.explore-product > div.explore-product-details > h3");
                  //  Log.d("TAG", productlist.toString());
//                    Traverse the content of the Elements object
                    for (int j = 0 ;j<7;j++) {

                        String title = productlistfortitle.get(j).select("a").text();//Get the title content
                        String imageUrl = productlist.get(j).select("img").attr("src");//Get image link
                        String appurl = "http://www.pling.com" + productlistfortitle.get(j).select("a").attr("href");
                                          array.add(new ChildModel(imageUrl,title, appurl));



                        Handler uiHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                ChildRecyclerViewAdapter
                                        childRecyclerViewAdapter = new          ChildRecyclerViewAdapter(array, holder.childRecyclerView.getContext());
                                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
                                holder.progressBar.setVisibility(View.INVISIBLE);

                            }
                        });



                    }
                 }
                catch (Exception e){
                    Log.d("TAG",e.toString());
                }
            }
        }.start();
    }
}
