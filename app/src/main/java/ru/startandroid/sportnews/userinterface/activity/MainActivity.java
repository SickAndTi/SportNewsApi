package ru.startandroid.sportnews.userinterface.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import ru.startandroid.sportnews.R;
import ru.startandroid.sportnews.userinterface.fragments.SportNewsFragment;

public class MainActivity extends AppCompatActivity {
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);

        Fragment fragment = new SportNewsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }
}
