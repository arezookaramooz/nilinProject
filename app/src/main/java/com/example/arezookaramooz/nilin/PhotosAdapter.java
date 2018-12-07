package com.example.arezookaramooz.nilin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.arezookaramooz.nilin.Data.Album;
import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.example.arezookaramooz.nilin.Data.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter {

    AlbumManager m;
    int albumId;
    ArrayList<Photo> photos = new ArrayList<Photo>();

    public PhotosAdapter(Context context, int albumId) {
        m = AlbumManager.getInstance(context);
        this.albumId = albumId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_row, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        Log.d("PhotosAdapter", "albumId is: " + albumId);

        Picasso.with(holder.itemView.getContext()).load(photos.get(position).getThumbnailUrl()).into(((MyViewHolder) holder).photo);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

//                Intent myIntent = new Intent(holder.itemView.getContext(), LargePhotoActivity.class);
//                myIntent.putExtra("albumPosition", albumPosition);
//                myIntent.putExtra("photoPosition", position);
//                ((MyViewHolder) holder).photo.getContext().startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return photos.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView photo;

        public MyViewHolder(View view) {
            super(view);
            photo = (ImageView) view.findViewById(R.id.photo_icon);

        }
    }


}
