package com.joma.encard02.ui.onBoard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.joma.encard02.R;
import com.joma.encard02.base.BaseFragment;
import com.joma.encard02.databinding.FragmentOnBoardBinding;
import com.joma.encard02.ui.Prefs;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OnBoardFragment extends BaseFragment<FragmentOnBoardBinding> implements BoardAdapter.ClickableListener {
    private BoardAdapter adapter;
    @Inject
    public Prefs prefs;

    @Override
    protected FragmentOnBoardBinding bind() {
        return FragmentOnBoardBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPager();
    }

    private void initPager() {
        adapter = new BoardAdapter(this);
        binding.boarPager.setAdapter(adapter);
        binding.wormDotsIndicator.setViewPager2(binding.boarPager);
    }

    @Override
    protected void setupUI() {

    }

    @Override
    protected void setupObservers() {

    }

    @Override
    public void click() {
        controller.navigate(R.id.navigation_words);
        prefs.saveBoardState();
    }
}