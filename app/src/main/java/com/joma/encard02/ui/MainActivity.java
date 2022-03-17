package com.joma.encard02.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.joma.encard02.R;
import com.joma.encard02.databinding.ActivityMainBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController controller;
    private NavHostFragment navHostFragment;
    @Inject
    public Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavView();
    }

    private void initNavView() {
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        controller = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.navView, controller);
        if (!prefs.isBoardShow()) {
            controller.navigate(R.id.navigation_on_board);
        }
        controller.addOnDestinationChangedListener((controller, destination, arguments) -> {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(R.id.navigation_words);
            list.add(R.id.navigation_video);

            if (list.contains(destination.getId())) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.GONE);
            }
        });
    }

}