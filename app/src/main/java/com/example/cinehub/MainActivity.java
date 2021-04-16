package com.example.cinehub;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.cinehub.NavigationFragments.BookingsFragment;
import com.example.cinehub.NavigationFragments.RunningInTheatersFragment;
import com.example.cinehub.NavigationFragments.SearchMovieFragment;
import com.example.cinehub.NavigationFragments.WhatchedMoviesFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById((R.id.drawer_layout));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle); //for screens readers
        toggle.syncState();

        if (savedInstanceState == null) { //poate fi null daca intram prima data in activitate sau daca dam back, pe rotate nu o sa fie null si nu o sa incarce fragmentul
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RunningInTheatersFragment()).commit(); //il deschide prima data cand se deschide aplicatia
//            navigationView.setCheckedItem(R.id.nav_running_in_theaters); //checks message in navigation bar
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchMovieFragment()).commit(); //il deschide prima data cand se deschide aplicatia
            navigationView.setCheckedItem(R.id.nav_search_movie); //checks message in navigation bar

        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START); ///close the menu bar if the drawer is opened
        } else {
            super.onBackPressed(); //close the activity on back pressed
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) { //navigare intre fragmente
            case R.id.nav_running_in_theaters:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RunningInTheatersFragment()).commit();
                break;
            case R.id.nav_bookings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BookingsFragment()).commit();
                break;
            case R.id.nav_watched_movies:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WhatchedMoviesFragment()).commit();
                break;
            case R.id.nav_search_movie:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchMovieFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
