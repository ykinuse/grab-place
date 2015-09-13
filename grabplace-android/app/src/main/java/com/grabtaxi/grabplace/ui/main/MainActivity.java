package com.grabtaxi.grabplace.ui.main;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.grabtaxi.grabplace.R;
import com.grabtaxi.grabplace.ui.ALocationActivity;
import com.grabtaxi.grabplace.ui.login.LoginActivity;

public class MainActivity extends ALocationActivity
{
    TextView mLocationText;

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
        this.mLocationText = (TextView) this.findViewById(R.id.location_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_logout:
            {
                LoginManager.getInstance().logOut();
                this.startActivity(LoginActivity.newIntent(this));
                this.finish();
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
            this.mLocationText.setText("Last Location = " + lastLocation.getLatitude() + ", " + lastLocation.getLongitude());
        }
    }
}
