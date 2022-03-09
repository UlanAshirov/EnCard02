package com.joma.encard02.ui.wordsFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.joma.encard02.base.BaseFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.databinding.FragmentWordsBinding;
import com.joma.encard02.ui.addWordsFragment.AddWordsFragment;

public class WordsFragment extends BaseFragment<FragmentWordsBinding> implements ISendKeyWord {
    private WordAdapter adapter;
    private WordViewModel viewModel;

    @Override
    protected FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    @Override
    protected void setupUI() {
        adapter = new WordAdapter();
        binding.rvImage.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(WordViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.liveData.observe(getViewLifecycleOwner(), pixabayResponseResource -> {
            switch (pixabayResponseResource.status) {
                case SUCCESS:
                    adapter.setListImage(pixabayResponseResource.data.getHits());
                    break;
                case LOADING:
                    Toast.makeText(requireContext(), "Ждите", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(requireContext(), "Упс", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void initListeners() {
        binding.btnAddWord.setOnClickListener(view -> {
            new AddWordsFragment(this).show(requireActivity().getSupportFragmentManager(), " ");
        });
    }

    @Override
    public void sendWord(String word) {
        Log.e("-------", word);
        viewModel.getImageByWord(word);
    }
}