package com.joma.encard02.ui.addWordsFragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
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
                    if (length == binding.etAddWord.getText().length()) {
                        keyWord.sendWord(binding.etAddWord.getText().toString());
                        if (img) {
                            controller.navigate(R.id.wordsFragment);
                        } else {
                            controller.navigate(R.id.videoFragment);
                        }
                    }
                }, 2000);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /*
        binding.etAddWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (keyEvent == null || !keyEvent.isShiftPressed()) {
                        keyWord.sendWord(binding.etAddWord.getText().toString());
                        if (img) {
                            controller.navigate(R.id.wordsFragment);
                        } else {
                            controller.navigate(R.id.videoFragment);
                        }
                    }
                }
                return true;
            }
        });*/
    }
}