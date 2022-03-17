package com.joma.encard02.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joma.encard02.R;
import com.joma.encard02.databinding.PageBoardBinding;

import org.jetbrains.annotations.NotNull;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private final ClickableListener listener;
    private PageBoardBinding boardBinding;

    private static final String category = "Категории";
    private static final String words = "Слова";
    private static final String learn = "Изучение";
    private static final String personalArea = "Личный Кабинет";
    private static final String letsGo = "Давай начнем!";

    private static final String categoryDes = "Создавайте собственные категории для слов";
    private static final String wordsDes = "Создавайте слова на английском и кликайте " +
            "на карточку чтобы увидеть его перевод и картинку для ассоциации";
    private static final String learnDes = "Свайпайте карточку вправо если вы запомнили," +
            "влево если пока ещё не уверены";
    private static final String personalAreaDes = "Следите за своими ачивками " +
            "и количеством очков опыта";

    public static int[] animateList = {R.raw.video_learning, R.raw.key_words, R.raw.learning,
            R.raw.child_learning, R.raw.book};
    private static final String[] titleList = {category, words, learn, personalArea, letsGo};
    private static final String[] descriptionList = {categoryDes, wordsDes, learnDes,
            personalAreaDes, " "};

    public BoardAdapter(ClickableListener listener) {
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        boardBinding = PageBoardBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BoardViewHolder(boardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BoardViewHolder holder, int position) {
        holder.onBoard(position);
        boardBinding.btnBoard.setOnClickListener(view -> listener.click());
    }

    @Override
    public int getItemCount() {
        return titleList.length;
    }

    public static class BoardViewHolder extends RecyclerView.ViewHolder {
        PageBoardBinding boardBinding;

        public BoardViewHolder(@NonNull PageBoardBinding boardBinding) {
            super(boardBinding.getRoot());
            this.boardBinding = boardBinding;
        }

        public void onBoard(int position) {
            boardBinding.lottieAnimBoard.setAnimation(animateList[position]);
            boardBinding.tvCategoryBoard.setText(titleList[position]);
            boardBinding.tvDescriptionBoard.setText(descriptionList[position]);

            if (position == animateList.length - 1) {
                boardBinding.btnBoard.setVisibility(View.VISIBLE);
                boardBinding.tvDescriptionBoard.setVisibility(View.GONE);
            } else {
                boardBinding.btnBoard.setVisibility(View.GONE);
            }
        }
    }

    interface ClickableListener {
        void click();
    }
}
