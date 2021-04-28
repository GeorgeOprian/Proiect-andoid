package com.example.cinehub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


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
    private NavigationView navigationView;
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

        initNavigationComponent();
        addUserInfoToMenuHeader(user, navigationView);
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


    private void initNavigationComponent() {
        drawer = findViewById((R.id.drawer_layout));
        navigationView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle); //for screens readers
        toggle.syncState();

        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
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
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.runningInTheatersFragment);
                break;
            case R.id.nav_bookings:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.bookingsFragment);
                break;

            case R.id.nav_search_movie:
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.searchMovieFragment);

                break;
            case R.id.nav_log_out:
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
