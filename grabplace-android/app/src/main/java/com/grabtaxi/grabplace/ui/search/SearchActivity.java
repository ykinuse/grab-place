package com.grabtaxi.grabplace.ui.search;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.grabtaxi.grabplace.R;
import com.grabtaxi.grabplace.ui.AActivity;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class SearchActivity extends AActivity
{
    private static final String TAG                    = SearchActivity.class.getSimpleName();
    private static final String EXTRA_CURRENT_LOCATION = "SearchActivity.EXTRA_CURRENT_LOCATION";

    public static Intent newIntent(final Context context, final Location location)
    {
        final Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(SearchActivity.EXTRA_CURRENT_LOCATION, location);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }
}
