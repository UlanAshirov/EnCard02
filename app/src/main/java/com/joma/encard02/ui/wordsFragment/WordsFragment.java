package com.joma.encard02.ui.wordsFragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
    private ISendKeyWord sendKeyWord;
    private int pageIn;

    private AddWordsFragment addWordsFragment;

    @Override
    protected FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        initRefresh();
    }

    private void initRefresh() {
        sendKeyWord = this;
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIn += pageIn;
                        binding.swipeRefresh.setRefreshing(false);
                    }
                }, 2000);
                binding.swipeRefresh.setColorSchemeResources(R.color.black,
                        R.color.white, R.color.red);
            }
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
            addWordsFragment = new AddWordsFragment(true, sendKeyWord);
            addWordsFragment.show(requireActivity().getSupportFragmentManager(), " ");
        });
    }

    @Override
    public void sendWord(String word, int page) {
        pageIn = page;
        Log.e("-------", word);
        viewModel.getImageByWord(word, pageIn);
        addWordsFragment.dismiss();
    }
}