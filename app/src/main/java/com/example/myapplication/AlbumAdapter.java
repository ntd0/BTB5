package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>{
    List<Album> list;

    public AlbumAdapter(List<Album> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.itv_album, parent, false);
        return new AlbumViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = list.get(position);

        holder.itvTvAlbumId.setText(album.getAlbumId() + "");
        holder.itvTvTitle.setText(album.getTitle());
        Picasso.get().load(album.getThumbnailUrl()).into(holder.itvImgThumbnailUrl);
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size() ;
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        private ImageView itvImgThumbnailUrl;
        private ConstraintLayout itvContentAlbum;
        private TextView itvTvAlbumId;
        private TextView itvTvTitle;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);

            itvImgThumbnailUrl = (ImageView) itemView.findViewById(R.id.itv_img_thumbnailUrl);
            itvContentAlbum = (ConstraintLayout) itemView.findViewById(R.id.itv_content_album);
            itvTvAlbumId = (TextView) itemView.findViewById(R.id.itv_tv_albumId);
            itvTvTitle = (TextView) itemView.findViewById(R.id.itv_tv_title);
        }
    }
}
