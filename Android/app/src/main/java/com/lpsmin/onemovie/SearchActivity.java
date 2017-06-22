package com.lpsmin.onemovie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.lpsmin.onemovie.R;
import com.lpsmin.onemovie.fragment.MovieFragment;
import com.lpsmin.onemovie.fragment.SearchFragment;
import com.lpsmin.onemovie.model.Movie;
import com.lpsmin.onemovie.model.MovieResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment fragment = new SearchFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_search, fragment).commit();
    }
}
