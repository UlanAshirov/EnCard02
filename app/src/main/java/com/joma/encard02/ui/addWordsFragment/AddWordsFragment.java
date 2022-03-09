package com.joma.encard02.ui.addWordsFragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.joma.encard02.R;
import com.joma.encard02.base.BaseBottomSheetDialogFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.databinding.FragmentAddWordsBinding;

public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {
    private ISendKeyWord keyWord;

    public AddWordsFragment(ISendKeyWord keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    protected FragmentAddWordsBinding bind() {
        return FragmentAddWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initClickers();
    }

    private void initClickers() {
        binding.btnAdd.setOnClickListener(view -> {
            keyWord.sendWord(binding.etAddWord.getText().toString());
            controller.navigate(R.id.wordsFragment);
        });
    }
}