package com.example.cinehub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.example.cinehub.SearchMovieAction.SearchResultsFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private SharedPreferences sharedPreferences;
    private FirebaseUser user;
    private boolean loggedInState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoginData();
        if (loggedInState == SignInActivity.USER_LOGED_OUT || user == null) {

            goToSignInActivity();

        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById((R.id.drawer_layout));

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addUserInfoToMenuHeader(user, navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle); //for screens readers
        toggle.syncState();

        SearchMovieFragment s = new SearchMovieFragment();


        if (savedInstanceState == null) { //poate fi null daca intram prima data in activitate sau daca dam back, pe rotate nu o sa fie null si nu o sa incarce fragmentul
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RunningInTheatersFragment()).commit(); //il deschide prima data cand se deschide aplicatia
//            navigationView.setCheckedItem(R.id.nav_running_in_theaters); //checks message in navigation bar
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchMovieFragment()).commit(); //il deschide prima data cand se deschide aplicatia
            navigationView.setCheckedItem(R.id.nav_search_movie); //checks message in navigation bar

        }
    }

    private void initLoginData (){
        sharedPreferences = this.getSharedPreferences(SignInActivity.LOGIN_SHARED_PREF, MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        loggedInState = sharedPreferences.getBoolean(SignInActivity.LOGIN_STATE, false);
    }

    private void addUserInfoToMenuHeader (FirebaseUser user, NavigationView navigationView) {
        View headerView = navigationView.getHeaderView(0);
        ImageView profileImage = headerView.findViewById(R.id.profile_image);
        TextView accountName = headerView.findViewById(R.id.account_name);
        TextView email = headerView.findViewById(R.id.email);

        Picasso.get().load(user.getPhotoUrl()).into(profileImage);
        accountName.setText(user.getDisplayName());
        email.setText(user.getEmail());
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
            case R.id.nav_log_out:
                //face log out
                //sterge din shared pref userul logat si te intoarce la activitatea de login, punand null pe obiectul salvat in shared pref
//                mAuth.signOut();
//                goToSignInActivity();
                signOut();
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void goToSignInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void signOut(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        saveLogOutState();
                        goToSignInActivity();
                    }
                });
    }

    private void saveLogOutState(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SignInActivity.LOGIN_STATE, SignInActivity.USER_LOGED_OUT);
        editor.apply();
    }
}
