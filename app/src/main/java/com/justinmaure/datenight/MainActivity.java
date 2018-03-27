package com.justinmaure.datenight;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener,
        MyDatesFragment.OnFragmentInteractionListener,
        FavoriteDateFragment.OnFragmentInteractionListener,
        CreateDateFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener {

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new MainFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_my_dates:
                fragment = new MyDatesFragment();
                break;
            case R.id.navigation_favorites:
                fragment = new FavoriteDateFragment();
                break;
            case R.id.navigation_main:
                fragment = new MainFragment();
                break;
            case R.id.navigation_create_date:
                fragment = new CreateDateFragment();
                break;
            case R.id.navigation_search:
                fragment = new SearchFragment();
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
