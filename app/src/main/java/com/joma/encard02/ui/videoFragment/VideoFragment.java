package com.joma.encard02.ui.videoFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Toast;

import com.joma.encard02.base.BaseFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.databinding.FragmentVideoBinding;
import com.joma.encard02.ui.addWordsFragment.AddWordsFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VideoFragment extends BaseFragment<FragmentVideoBinding> implements ISendKeyWord {
    private VideoAdapter adapter;
    public VideoViewModel viewModel;
    private AddWordsFragment addWordsFragment;

    @Override
    protected FragmentVideoBinding bind() {
        return FragmentVideoBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
    }

    private void initClickers() {
        binding.btnSearchVideo.setOnClickListener(view -> {
            addWordsFragment = new AddWordsFragment(false, this);
            addWordsFragment.show(requireActivity().getSupportFragmentManager(), " ");
        });
    }

    @Override
    protected void setupUI() {
        adapter = new VideoAdapter();
        binding.rvVideo.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(VideoViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), mainVideoResponceResource -> {
            switch (mainVideoResponceResource.status) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                    binding.progressVideo.setVisibility(View.GONE);
                    adapter.setVideoHits(mainVideoResponceResource.data.getVideoHits());
                    break;
                case LOADING:
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    @Override
    public void sendWord(String word, int page) {
        viewModel.getVideoByTag(word, page);
        addWordsFragment.dismiss();
    }
}