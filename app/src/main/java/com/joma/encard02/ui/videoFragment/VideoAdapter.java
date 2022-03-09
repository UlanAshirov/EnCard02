package com.joma.encard02.ui.videoFragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joma.encard02.data.videoModel.VideoHit;
import com.joma.encard02.data.videoModel.Videos;
import com.joma.encard02.databinding.ItemVideoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private ItemVideoBinding binding;
    private List<VideoHit> videoHits = new ArrayList<>();

    public void setVideoHits(List<VideoHit> videoHits) {
        this.videoHits = videoHits;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new VideoViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
        holder.onBindVideo(videoHits.get(position));
    }

    @Override
    public int getItemCount() {
        return videoHits.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBindVideo(VideoHit videoHit) {
            Log.e("ABOBA", videoHit.getVideos().getSmall().getUrl() );
           binding.itemImgVideo.videoUrl(videoHit.getVideos().getSmall().getUrl()).enableAutoStart();
            binding.tvItemTags.setText(videoHit.getTags());
        }
    }
}
