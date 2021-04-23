package com.example.ricerkit.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricerkit.AppContentsActivity;
import com.example.ricerkit.Models.CatOwl;
import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.R;
import com.example.ricerkit.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CatOwlAdapter extends  RecyclerView.Adapter<CatOwlAdapter.MyViewHolder> {

    Context cxt;
    public ArrayList<CatOwl> catOwlArrayList;


    public static class MyViewHolder extends
            RecyclerView.ViewHolder {

        public Button catslideName;
        public RecyclerView categoriesSlider;
        public MyViewHolder(View itemView) {
            super(itemView);
            catslideName = itemView.findViewById(R.id.catSlideName);
            categoriesSlider = itemView.findViewById(R.id.catcarousel);
        }
    }

    public CatOwlAdapter(ArrayList<CatOwl> arrayList, Context mContext) {
        this.cxt = mContext;
        this.catOwlArrayList = arrayList;
    }

    @Override
    public CatOwlAdapter.MyViewHolder
    onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoriesrecycler, parent, false);
        return new CatOwlAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatOwlAdapter.MyViewHolder holder, int position) {
        CatOwl currentItem = catOwlArrayList.get(position);

                //holder.heroImage.setImageResource(currentItem.getHeroImage());
        holder.catslideName.setText(currentItem.getCatslideName());
        holder.catslideName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), SearchResult.class);
                Bundle extras = new Bundle();
                extras.putString("Search Query", currentItem.getCatslideUrl());
                extras.putString("Search Word", currentItem.getCatslideName());
                intent.putExtras(extras);

                v.getContext().startActivity(intent);
            }
        });




    }



    @Override
    public int getItemCount() {
        return catOwlArrayList.size();
    }
}