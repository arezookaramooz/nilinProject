package com.example.arezookaramooz.nilin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arezookaramooz.nilin.Data.Album;
import com.example.arezookaramooz.nilin.Data.AlbumManager;
import com.example.arezookaramooz.nilin.Data.UserManager;


public class AlbumAdapter extends RecyclerView.Adapter {
    AlbumManager m;
    UserManager userManager;

    public AlbumAdapter(Context context) {
        m = AlbumManager.getInstance(context);
        userManager = UserManager.getInstance(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Album album = m.getAlbums().get(position);

        ((MyViewHolder) holder).userId.setText("" + album.getUserId());
        ((MyViewHolder) holder).id.setText("" + userManager.getNameWithId(album.getUserId()));
        ((MyViewHolder) holder).title.setText(album.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent myIntent = new Intent(v.getContext(), PhotosActivity.class);
                myIntent.putExtra("albumId", m.getAlbums().get(position).getId());
                v.getContext().startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return m.getAlbums().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, userId, id;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.album_title);
            userId = view.findViewById(R.id.album_userId);
            id = view.findViewById(R.id.album_id);
        }
    }
}