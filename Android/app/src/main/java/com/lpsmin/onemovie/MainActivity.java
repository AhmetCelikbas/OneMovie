package com.lpsmin.onemovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lpsmin.onemovie.fragment.MovieFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle args = new Bundle();
        args.putString("param_nav", "in_theaters");
        Fragment fragment = new MovieFragment();
        fragment.setArguments(args);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, fragment).commit();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu,
        // adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Create and setup bundle args
        Bundle args = new Bundle();
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_in_theaters:
                fragment = new MovieFragment();
                args.putString("param_nav", "in_theaters");
                break;
            case R.id.nav_popular:
                fragment = new MovieFragment();
                args.putString("param_nav", "popular");
                break;
            case R.id.nav_top_rated:
                fragment = new MovieFragment();
                args.putString("param_nav", "top_rated");
                break;
            case R.id.nav_upcoming:
                fragment = new MovieFragment();
                args.putString("param_nav", "upcoming");
                break;
            case R.id.nav_favorites:
                args.putString("param_nav", "favorites");
                Toast.makeText(this, "Fonctionnalité à venir", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_to_watch:
                args.putString("param_nav", "to_watch");
                Toast.makeText(this, "Fonctionnalité à venir", Toast.LENGTH_SHORT).show();
                break;
            /*
            case R.id.nav_logout:
                args.putString("item_type", "Logout");
                Toast.makeText(this, "Authentification à venir", Toast.LENGTH_SHORT).show();
                break;
            */
            case R.id.nav_about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = getLayoutInflater().inflate(R.layout.dialog_about, null);
                builder.setView(view);
                builder.show();
                break;
        }

        if (fragment != null) {
            args.putString("title", item.getTitle().toString());
            fragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
