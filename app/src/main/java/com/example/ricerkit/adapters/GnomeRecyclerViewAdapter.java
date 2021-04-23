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

public class GnomeRecyclerViewAdapter  extends RecyclerView.Adapter<GnomeRecyclerViewAdapter.MyViewHolder> {

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

    public GnomeRecyclerViewAdapter(ArrayList<ParentModel>
                                            exampleList, Context context) {
        this.parentModelArrayList = exampleList;
        this.cxt = context;

    }

    @Override
    public GnomeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_recyclerview_items, parent, false);
        return new GnomeRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return parentModelArrayList.size();
    }

    @Override
    public void onBindViewHolder(GnomeRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.progressBar.setVisibility(View.VISIBLE);
        ParentModel currentItem = parentModelArrayList.get(position);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setHasFixedSize(true);
        holder.category.setText(currentItem.movieCategory());

        ArrayList<ChildModel> arrayList = new ArrayList<>();
// added the first child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME Latest")) {
//
            linktoparse = "http://gnome-look.org/browse/cat";
            myThread(holder, linktoparse);
        }
// added in second child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME Highest Rating")) {
            linktoparse = "http://gnome-look.org/browse/ord/rating";
            myThread(holder, linktoparse);
        }
// added in third child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME Extensions")) {
            linktoparse = "http://gnome-look.org/browse/cat/156/order/latest";
            myThread(holder, linktoparse);
        }
// added in fourth child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME 2 Splash Screens")) {
            linktoparse = "http://gnome-look.org/browse/cat/130/order/latest";
            myThread(holder, linktoparse);
        }
// added in fifth child row
        if
        (parentModelArrayList.get(position).movieCategory().equals("GTK2 Themes")) {
            linktoparse = "http://gnome-look.org/browse/cat/136/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GTK 3/4 Themes")) {
            linktoparse = "http://gnome-look.org/browse/cat/135/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Cursors")) {
            linktoparse = "http://gnome-look.org/browse/cat/107/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME Shell Themes")) {
            linktoparse = "http://gnome-look.org/browse/cat/134/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Cairo Clock")) {
            linktoparse = "http://gnome-look.org/browse/cat/208/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Docks")) {
            linktoparse = "http://gnome-look.org/browse/cat/277/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GDM Themes")) {
            linktoparse = "http://gnome-look.org/browse/cat/131/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GnoMenu Skins")) {
            linktoparse = "http://gnome-look.org/browse/cat/127/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME App Addons")) {
            linktoparse = "http://gnome-look.org/browse/cat/382/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME 2 Color Schemes")) {
            linktoparse = "http://gnome-look.org/browse/cat/136/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Grub Themes")) {
            linktoparse = "http://gnome-look.org/browse/cat/109/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Metacity Themes")) {
            linktoparse = "http://gnome-look.org/browse/cat/125/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GIMP")) {
            linktoparse = "http://gnome-look.org/browse/cat/190/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("Various GNOME Theming")) {
            linktoparse = "http://gnome-look.org/browse/cat/566/order/latest";
            myThread(holder, linktoparse);
        }
        if
        (parentModelArrayList.get(position).movieCategory().equals("GNOME Wallpapers")) {
            linktoparse = "http://gnome-look.org/browse/cat/300/order/latest";
            myThread(holder, linktoparse);
        }



        }

    public void myThread(GnomeRecyclerViewAdapter.MyViewHolder holder, String linktoparse) {
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