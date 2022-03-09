package com.joma.encard02.ui.videoFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Toast;

import com.joma.encard02.R;
import com.joma.encard02.base.BaseFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.common.Resource;
import com.joma.encard02.data.videoModel.MainVideoResponce;
import com.joma.encard02.databinding.FragmentVideoBinding;
import com.joma.encard02.ui.App;
import com.joma.encard02.ui.addWordsFragment.AddWordsFragment;

public class VideoFragment extends BaseFragment<FragmentVideoBinding> implements ISendKeyWord {
    private VideoAdapter adapter;
    private VideoViewModel viewModel;
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
            addWordsFragment = new AddWordsFragment(false ,this);
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
        viewModel.liveData.observe(getViewLifecycleOwner(), mainVideoResponceResource -> {
            switch (mainVideoResponceResource.status) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Запрос успешен", Toast.LENGTH_SHORT).show();
                    adapter.setVideoHits(mainVideoResponceResource.data.getVideoHits());
                    binding.progressVideo.setVisibility(View.GONE);
                    break;
                case LOADING:
                    Toast.makeText(requireContext(), "Ждите", Toast.LENGTH_SHORT).show();
                    binding.progressVideo.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), "Упс", Toast.LENGTH_SHORT).show();
                    binding.progressVideo.setVisibility(View.GONE);
                    break;
            }
        });
    }

    @Override
    public void sendWord(String word) {
        viewModel.getVideoByTag(word);
        addWordsFragment.dismiss();
    }
}