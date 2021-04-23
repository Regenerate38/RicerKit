package com.example.ricerkit.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ricerkit.AppContentsActivity;
import com.example.ricerkit.Models.ChildModel;
import com.example.ricerkit.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChildRecyclerViewAdapter extends
        RecyclerView.Adapter<ChildRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<ChildModel>
            childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView heroImage;
        public TextView movieName;


        public MyViewHolder(View itemView) {
            super(itemView);
            heroImage =  itemView.findViewById(R.id.hero_image);
            movieName =  itemView.findViewById(R.id.movie_name);



        }


    }

    public ChildRecyclerViewAdapter(ArrayList<ChildModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ChildModel currentItem = childModelArrayList.get(position);
        Picasso.get().load(currentItem.getHeroImage()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.drawable.loadingplaceholder).error(R.drawable.archlinux).into(holder.heroImage);
        //holder.heroImage.setImageResource(currentItem.getHeroImage());
        holder.movieName.setText(currentItem.getMovieName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AppContentsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("Name", currentItem.getMovieName());
                extras.putString("ImageUrl", currentItem.getHeroImage());
                extras.putString("MainUrl", currentItem.getProductUrl());
                intent.putExtras(extras);
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return childModelArrayList.size();
    }
}