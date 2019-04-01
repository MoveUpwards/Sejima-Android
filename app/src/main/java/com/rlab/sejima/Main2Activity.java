package com.rlab.sejima;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.rlab.sejima.fragments.DefaultFragment;
import com.rlab.sejima.fragments.FragmentMUAvatar;
import com.rlab.sejima.fragments.FragmentMUButton;
import com.rlab.sejima.fragments.FragmentMUHeader;
import com.rlab.sejima.fragments.FragmentMUNavigationBar;
import com.rlab.sejima.fragments.FragmentMUTopBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_mu_avatar) {
            fragment = FragmentMUAvatar.newInstance();
        } else if (id == R.id.nav_mu_header) {
            fragment = FragmentMUHeader.newInstance();
        } else if (id == R.id.nav_mu_topbar) {
            fragment = FragmentMUTopBar.newInstance();
        } else if (id == R.id.nav_mu_button) {
            fragment = FragmentMUButton.newInstance();
        } else if (id == R.id.nav_mu_navigationbar) {
            fragment = FragmentMUNavigationBar.newInstance();
        } else  {
            fragment = PlaceholderFragment.newInstance(0);
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class PlaceholderFragment extends Fragment {

        private static String COMPONENT = "component-to-launch";

        public static PlaceholderFragment newInstance(int component){
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(COMPONENT, component);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }
}
