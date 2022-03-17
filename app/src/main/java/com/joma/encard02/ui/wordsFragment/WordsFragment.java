package com.joma.encard02.ui.wordsFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.joma.encard02.R;
import com.joma.encard02.base.BaseFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.databinding.FragmentWordsBinding;
import com.joma.encard02.ui.addWordsFragment.AddWordsFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WordsFragment extends BaseFragment<FragmentWordsBinding> implements ISendKeyWord {
    private WordAdapter adapter;
    public WordViewModel viewModel;
    private AddWordsFragment addWordsFragment;
    private String wordIn;
    private int pageIn = 1;

    @Override
    protected FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addWordsFragment = new AddWordsFragment(true, this);
        initListeners();
        initRefresh();
    }

    private void initRefresh() {
        binding.swipeRefresh.setOnRefreshListener(() -> {
            if (getArguments() != null) {
                wordIn = getArguments().getString("word");
                Log.e("refresh", wordIn + "");
                viewModel.getImageByWord(wordIn, pageIn += 1);
            }
            binding.swipeRefresh.setRefreshing(false);
            binding.swipeRefresh.setColorSchemeResources(R.color.black,
                    R.color.white, R.color.red);
        });
    }

    @Override
    protected void setupUI() {
        adapter = new WordAdapter();
        binding.rvImage.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(WordViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), pixabayResponseResource -> {
            switch (pixabayResponseResource.status) {
                case SUCCESS:
                    binding.progress.setVisibility(View.GONE);
                    Log.e("refresh+", "Иштеп жатат");
                    adapter.setListImage(pixabayResponseResource.data.getHits());
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

    private void initListeners() {
        binding.btnAddWord.setOnClickListener(view -> {
            addWordsFragment.show(requireActivity().getSupportFragmentManager(), " ");
        });
    }

    @Override
    public void sendWord(String word, int page) {
        viewModel.getImageByWord(word, page);
        addWordsFragment.dismiss();
    }
}