package com.example.ricerkit.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ricerkit.FullScreenView;
import com.example.ricerkit.Models.GalleryModel;
import com.example.ricerkit.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class GalleryRecyclerViewAdapter  extends RecyclerView.Adapter<GalleryRecyclerViewAdapter.MyViewHolder> {

    public ArrayList<GalleryModel> GalleryModelArrayList;
    Context cxt;

public static class MyViewHolder extends
        RecyclerView.ViewHolder {
    public ImageView heroImage;



    public MyViewHolder(View itemView) {
        super(itemView);
        heroImage =  itemView.findViewById(R.id.hero_image);



    }


}

    public GalleryRecyclerViewAdapter(ArrayList<GalleryModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.GalleryModelArrayList = arrayList;
    }

    @Override
    public GalleryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_recycler_items, parent, false);
        return new GalleryRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryRecyclerViewAdapter.MyViewHolder holder, int position) {
        GalleryModel currentItem = GalleryModelArrayList.get(position);
        Picasso.get().load(currentItem.getHero_image()).placeholder(R.drawable.loadingplaceholder).into(holder.heroImage);

        holder.heroImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(v.getContext(), FullScreenView.class);
                intent.putExtra("ImageUrl", currentItem.getHero_image());
                intent.putExtra("Position", position);
                v.getContext().startActivity(intent);

            }
        });



    }



    @Override
    public int getItemCount() {
        return GalleryModelArrayList.size();
    }
}