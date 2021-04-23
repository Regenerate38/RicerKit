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
import com.example.ricerkit.Models.SearchModel;
import com.example.ricerkit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchResultRecyclerViewAdapter extends        RecyclerView.Adapter<SearchResultRecyclerViewAdapter.MyViewHolder> {
    public ArrayList<SearchModel> childModelArrayList;
    Context cxt;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView heroImage;
        public TextView movieName, CategoryBox, AuthorBox;



        public MyViewHolder(View itemView) {
            super(itemView);
            heroImage =  itemView.findViewById(R.id.hero_image);
            movieName =  itemView.findViewById(R.id.movie_name);
            CategoryBox = itemView.findViewById(R.id.tv_app_type);
            AuthorBox = itemView.findViewById(R.id.tv_app_developer);

        }


    }

    public SearchResultRecyclerViewAdapter(ArrayList<SearchModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.childModelArrayList = arrayList;
    }

    @Override
    public SearchResultRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_items, parent, false);
        return new SearchResultRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultRecyclerViewAdapter.MyViewHolder holder, int position) {
        SearchModel currentItem = childModelArrayList.get(position);
        Picasso.get().load(currentItem.getHero_image()).placeholder(R.drawable.kdelogo).error(R.drawable.xfce).into(holder.heroImage);
        //holder.heroImage.setImageResource(currentItem.getHeroImage());
        holder.movieName.setText(currentItem.getMovieName());
        holder.AuthorBox.setText(currentItem.getAuthorname());
        holder.CategoryBox.setText(currentItem.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AppContentsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("Name", currentItem.getMovieName());
                extras.putString("ImageUrl", currentItem.getHero_image());
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