package com.joma.encard02.ui.wordsFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joma.encard02.data.model.Hit;
import com.joma.encard02.data.model.PixabayResponse;
import com.joma.encard02.databinding.ItemRvImageBinding;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private ItemRvImageBinding binding;
    private List<Hit> listImage = new ArrayList<>();

    public void setListImage(List<Hit> listImage) {
        this.listImage = listImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvImageBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new WordViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.WordViewHolder holder, int position) {
        holder.onBind(listImage.get(position));
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(Hit hit) {
            Glide.with(binding.getRoot()).load(hit.getLargeImageURL()).centerCrop()
                    .into(binding.itemImage);
        }
    }
}
