package com.justinmaure.datenight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.justinmaure.datenight.Objects.User;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        MainFragment.OnFragmentInteractionListener,
        MyDatesFragment.OnFragmentInteractionListener,
        FavoriteDateFragment.OnFragmentInteractionListener,
        CreateDateFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        SettingsFragment.OnFragmentInteractionListener,
        UpdateDateFragment.OnFragmentInteractionListener,
        CreditsFragment.OnFragmentInteractionListener,
        ContactUsFragment.OnFragmentInteractionListener {


    public static User currentUser = LoginActivity.user;
    public static FloatingActionButton fab;
    public static BottomNavigationView navigation;

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

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new CreateDateFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        fab.hide();

        //This will change to main fragment later
        loadFragment(new MyDatesFragment());
        //Use if statement to check if user is logged in. if not send them to the login page. if so then launch main fragment
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
            case R.id.navigation_settings:
                fragment = new SettingsFragment();
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
