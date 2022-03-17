package com.joma.encard02.ui.wordsFragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.joma.encard02.data.model.imageModel.Hit;
import com.joma.encard02.databinding.ItemRvImageBinding;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private List<Hit> listImage = new ArrayList<>();

    public void setListImage(List<Hit> listImage) {
        this.listImage = listImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRvImageBinding binding = ItemRvImageBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new WordViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapter.WordViewHolder holder, int position) {
        holder.onBind(listImage.get(position));
    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {
        ItemRvImageBinding binding;
        public WordViewHolder(@NonNull ItemRvImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Hit hit) {
            Glide.with(binding.getRoot()).load(hit.getLargeImageURL()).centerCrop()
                    .into(binding.itemImage);
        }
    }
}
