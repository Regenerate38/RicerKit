package com.example.ricerkit.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.Models.ParentModel;
import com.example.ricerkit.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class XfceRecyclerViewAdapter extends
        RecyclerView.Adapter<XfceRecyclerViewAdapter.MyViewHolder> {

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

    public XfceRecyclerViewAdapter(ArrayList<ParentModel> exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;

    }

    @Override
    public XfceRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
        return new XfceRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(XfceRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.progressBar.setVisibility(View.VISIBLE);
        ParentModel currentItem = parentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);
        holder.category.setText(currentItem.movieCategory());

// added the first child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("XFCE Latest")) {
//
            linktoparse = "http://xfce-look.org/browse/cat";
            myThread(holder, linktoparse);
        }
// added in second child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("XFCE Highest Rating")) {
            linktoparse = "http://xfce-look.org/browse/ord/rating";
            myThread(holder, linktoparse);
        }
// added in third child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Xfce Themes")) {
            linktoparse = "http://xfce-look.org/browse/cat/138/order/latest";
            myThread(holder, linktoparse);
        }
// added in fourth child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("Compiz Themes")) {
            linktoparse = "http://xfce-look.org/browse/cat/116/order/latest";
            myThread(holder, linktoparse);
        }
// added in fifth child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("GTK2 Themes")) {
            linktoparse = "http://xfce-look.org/browse/cat/136/order/latest";
            myThread(holder, linktoparse);
        }

        if
        (parentModelArrayList.get(position).movieCategory().equals("GTK3/4 Themes")) {
            linktoparse = "http://xfce-look.org/browse/cat/135/order/latest";
            myThread(holder, linktoparse);
        }

        if
        (parentModelArrayList.get(position).movieCategory().equals("XFCE Wallpapers")) {
            linktoparse = "http://xfce-look.org/browse/cat/302/order/latest";
            myThread(holder, linktoparse);
        }

        if
        (parentModelArrayList.get(position).movieCategory().equals("Beryl Themes")) {
            linktoparse = "http://xfce-look.org/browse/cat/117/order/latest";
            myThread(holder, linktoparse);
        }

        if
        (parentModelArrayList.get(position).movieCategory().equals("XFCE Screenshots")) {
            linktoparse = "http://xfce-look.org/browse/cat/256/order/latest";
            myThread(holder, linktoparse);
        }

        if
        (parentModelArrayList.get(position).movieCategory().equals("Cursors")) {
            linktoparse = "http://xfce-look.org/browse/cat/107/order/latest";
            myThread(holder, linktoparse);
        }



    }

    public void myThread(XfceRecyclerViewAdapter.MyViewHolder holder, String linktoparse) {
        new Thread() {
            @Override
            public void run() {
                try {
                    ArrayList<ChildModel> array = new ArrayList<>();

//                    Create a Document object to link to the target website to obtain
                    Document doc = Jsoup.connect(linktoparse).get();
                    //Create an Elements object to get all the contents of div.slist
                    Elements productlist = doc.select("div.product-list > div.explore-product > div.explore-product-imgcolumn > figure > a > div.text-center.imageContainer_");
                    Elements productlistfortitle = doc.select("div.product-list > div.explore-product > div.explore-product-details > h3");
                    //  Log.d("TAG", productlist.toString());
//                    Traverse the content of the Elements object
                    for (int j = 0; j < 7; j++) {

                        String title = productlistfortitle.get(j).select("a").text();//Get the title content
                        String imageUrl = productlist.get(j).select("img").attr("src");//Get image link
                        String appurl = "http://www.pling.com" + productlistfortitle.get(j).select("a").attr("href");
                        array.add(new ChildModel(imageUrl, title, appurl));


                        Handler uiHandler = new Handler(Looper.getMainLooper());
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                ChildRecyclerViewAdapter
                                        childRecyclerViewAdapter = new ChildRecyclerViewAdapter(array, holder.childRecyclerView.getContext());
                                holder.childRecyclerView.setAdapter(childRecyclerViewAdapter);
                                holder.progressBar.setVisibility(View.INVISIBLE);
                            }
                        });


                    }
                } catch (Exception e) {
                    Log.d("TAG", e.toString());
                }
            }
        }.start();
    }
}
