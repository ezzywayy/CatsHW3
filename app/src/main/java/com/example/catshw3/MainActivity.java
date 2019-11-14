package com.example.catshw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Fragment fragment = new CatSearchFragment();
        swapFragment(new CatSearchFragment());

        bottomNavigationview = findViewById(R.id.nav_bar);
        bottomNavigationview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // menuItem = the item on the bottom nav view that was selected
                // The id's here belong to the items in the menu of the BottomnNavigationView
                // The menu is chunked out as bottom_nav_menu.xml in the res > menu folder
                if (menuItem.getItemId() == R.id.navigation_search) {
                    Fragment fragment = new CatSearchFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.navigation_favourites) {
                    Fragment fragment = new FavouritesFragment();

                    // Here's just an example of passing information to the Fragment via Bundle
                    Bundle bundle = new Bundle();
                    bundle.putString("param1", "First Argument");
                    bundle.putString("param2", "Second Argument");
                    fragment.setArguments(bundle);
                    // End bundle part

                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Helper method to change the fragment displayed in the activity. We put this here so we don't
     * have to repeat the code every time we want to saw
     * @param fragment: instance of the fragment to go into the slot
     */
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }
}
