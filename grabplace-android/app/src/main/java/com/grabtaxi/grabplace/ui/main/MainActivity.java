package com.grabtaxi.grabplace.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.grabtaxi.grabplace.R;
import com.grabtaxi.grabplace.ui.ALocationActivity;
import com.grabtaxi.grabplace.ui.search.SearchActivity;

public class MainActivity extends ALocationActivity
{
    RelativeLayout        mMainContent;
    DrawerLayout          mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar               mToolbar;
    View                  searchButton;


    public static Intent newIntent(final Context context)
    {
        final Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mMainContent = (RelativeLayout) findViewById(R.id.main_content_view);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        this.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(this.mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, this.mToolbar, R.string.action_drawer_open,
                R.string.action_drawer_close)
        {
            @Override
            public void onDrawerClosed(View view)
            {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset)
            {
                super.onDrawerSlide(drawerView, slideOffset);
                if (MainActivity.this.mMainContent != null)
                {
                    MainActivity.this.mMainContent.setTranslationX(slideOffset * drawerView.getWidth());
                }

                if (MainActivity.this.mDrawerLayout != null)
                {
                    MainActivity.this.mDrawerLayout.bringChildToFront(drawerView);
                    MainActivity.this.mDrawerLayout.requestLayout();
                }
            }
        };

        // Set the drawer toggle as the DrawerListener
        this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
        this.mDrawerLayout.setScrimColor(Color.TRANSPARENT);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        final MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.searchButton = MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_search:
            {
                this.startActivity(SearchActivity.newIntent(this, this.getLastKnownLocation()));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(final Location location)
    {
        super.onLocationChanged(location);
        final Location lastLocation = this.getLastKnownLocation();
        if (lastLocation != null)
        {
        }
    }
}
