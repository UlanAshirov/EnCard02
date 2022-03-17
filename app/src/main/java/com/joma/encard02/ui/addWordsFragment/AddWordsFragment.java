package com.joma.encard02.ui.addWordsFragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.joma.encard02.R;
import com.joma.encard02.base.BaseBottomSheetDialogFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.databinding.FragmentAddWordsBinding;

public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {
    private ISendKeyWord keyWord;
    private Boolean img;
    private Handler handler;
    private int page = 1;
    private String word;

    public AddWordsFragment() {
    }

    public AddWordsFragment(boolean img, ISendKeyWord keyWord) {
        this.keyWord = keyWord;
        this.img = img;
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
        binding.etAddWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = binding.etAddWord.getText().length();
                handler = new Handler();
                handler.postDelayed(() -> {
                    word = binding.etAddWord.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("word", word);
                    if (length == word.length()) {
                        Log.e("refresh", word + "");
                        dismiss();
                        if (img) {
                            controller.navigate(R.id.navigation_words, bundle);
                        } else {
                            controller.navigate(R.id.navigation_video);
                        }
                        keyWord.sendWord(word, page);
                    }
                }, 2000);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}