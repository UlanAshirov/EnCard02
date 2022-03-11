package com.joma.encard02.ui.addWordsFragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.joma.encard02.R;
import com.joma.encard02.base.BaseBottomSheetDialogFragment;
import com.joma.encard02.common.ISendKeyWord;
import com.joma.encard02.databinding.FragmentAddWordsBinding;

public class AddWordsFragment extends BaseBottomSheetDialogFragment<FragmentAddWordsBinding> {
    private ISendKeyWord keyWord;
    private Boolean img;

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
        binding.etAddWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        keyEvent != null &&
                                keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
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
        });
    }
}