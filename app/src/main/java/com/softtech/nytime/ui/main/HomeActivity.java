package com.softtech.nytime.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.softtech.nytime.R;
import com.softtech.nytime.ui.BaseActivity;

import timber.log.Timber;
/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class HomeActivity
        extends BaseActivity {

    public static final String TAG_HOME_FRAGMENT = "home";

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private MenuItem search;

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        listeners();

        navigationView.post(new Runnable() {
            @Override
            public void run() {
                navigationView.setCheckedItem(R.id.nav_home);
                navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void listeners() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            Fragment fragment;
            if (menuItem.getItemId() == R.id.nav_about) {
                fragment = AboutFragment.create();
                if (search != null) search.setVisible(false);
            } else {
                fragment = HomeFragment.create();
                if (search != null) search.setVisible(true);
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment, TAG_HOME_FRAGMENT)
                    .commit();

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);

        search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String str) {
                Timber.i("onQueryTextChange: " + str);
                HomeFragment frg = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOME_FRAGMENT);
                if (frg != null) {
                    frg.search(str);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
