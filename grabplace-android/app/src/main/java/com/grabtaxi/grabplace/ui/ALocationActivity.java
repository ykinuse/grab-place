package com.grabtaxi.grabplace.ui;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.grabtaxi.grabplace.GPApplication;
import com.grabtaxi.grabplace.utils.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class ALocationActivity extends AActivity implements GoogleApiClient.ConnectionCallbacks, LocationListener
{
    private static final float           LOCATION_DISPLACEMENT              = 20f;
    private static final long            LOCATION_UPDATE_INTERVAL           = TimeUnit.MINUTES.toMillis(1);
    private static final LocationRequest GOOGLE_API_CLIENT_LOCATION_REQUEST = LocationRequest.create().setPriority(
            LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(ALocationActivity.LOCATION_UPDATE_INTERVAL).setSmallestDisplacement(
            ALocationActivity.LOCATION_DISPLACEMENT);

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onPause()
    {
        super.onPause();
        stopGoogleApiClient();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        stopGoogleApiClient();

        if (GPApplication.IS_GOOGLE_PLAY_SERVICE_AVAILABLE)
        {
            Logger.debug(ALocationActivity.class.getSimpleName(), "Start Google Api Client");
            this.mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).addConnectionCallbacks(this).build();
            this.mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnected(final Bundle bundle)
    {
        Logger.debug(ALocationActivity.class.getSimpleName(),"onConnected()");

        // request location update
        LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient,
                ALocationActivity.GOOGLE_API_CLIENT_LOCATION_REQUEST, this);
    }

    @Override
    public void onConnectionSuspended(final int i)
    {
        // Do nothing
        Logger.debug(ALocationActivity.class.getSimpleName(),"onConnectionSuspended()");
    }

    @Override
    public void onLocationChanged(final Location location)
    {
        // Do nothing
        Logger.debug(ALocationActivity.class.getSimpleName(),"onLocationChanged()");
    }

    @Nullable
    public Location getLastKnownLocation()
    {
        Logger.debug(ALocationActivity.class.getSimpleName(),"getLastKnownLocation()");
        try
        {
            if (this.mGoogleApiClient != null && this.mGoogleApiClient.isConnected())
            {
                return LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
            }
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void stopGoogleApiClient()
    {
        Logger.debug(ALocationActivity.class.getSimpleName(),"stopGoogleApiClient()");
        try
        {
            if (this.mGoogleApiClient != null && this.mGoogleApiClient.isConnected())
            {
                LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, this);
                this.mGoogleApiClient.disconnect();
            }
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
        }

        this.mGoogleApiClient = null;
    }
}
