package com.example.ricerkit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ricerkit.Models.CommentModel;
import com.example.ricerkit.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.MyViewHolder> {

    public ArrayList<CommentModel> CommentModelArrayList;
    Context cxt;

    public static class MyViewHolder extends
            RecyclerView.ViewHolder {
        public ImageView commentuserimage;
        public TextView commentuserName, commentuploadTime, commentdetails;



        public MyViewHolder(View itemView) {
            super(itemView);
            commentuserimage =  itemView.findViewById(R.id.comment_userimage);
            commentuserName = itemView.findViewById(R.id.comment_username);
            commentuploadTime = itemView.findViewById(R.id.comment_uploadtime);
            commentdetails = itemView.findViewById(R.id.comment_details);



        }


    }

    public CommentRecyclerViewAdapter(ArrayList<CommentModel> arrayList, Context mContext) {
        this.cxt = mContext;
        this.CommentModelArrayList = arrayList;
    }

    @Override
    public CommentRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_recyclerview_items, parent, false);
        return new CommentRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentRecyclerViewAdapter.MyViewHolder holder, int position) {
        CommentModel currentItem = CommentModelArrayList.get(position);
        Picasso.get().load(currentItem.getCommentuserimage()).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(R.drawable.loadingplaceholder).into(holder.commentuserimage);
        holder.commentuserName.setText(currentItem.getCommentusername());
        holder.commentuploadTime.setText(currentItem.getCommentuplaodtime());
        holder.commentdetails.setText(currentItem.getCommentdetails());




    }



    @Override
    public int getItemCount() {
        return CommentModelArrayList.size();
    }
}
    




