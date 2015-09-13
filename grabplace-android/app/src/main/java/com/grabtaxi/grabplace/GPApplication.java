package com.grabtaxi.grabplace;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.grabtaxi.grabplace.utils.Logger;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class GPApplication extends Application
{
    private static final String  TAG                              = GPApplication.class.getSimpleName();
    public static        boolean IS_GOOGLE_PLAY_SERVICE_AVAILABLE = false;

    @Override
    public void onCreate()
    {
        super.onCreate();

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        // Retrieve Google Play Service status
        try
        {
            GPApplication.IS_GOOGLE_PLAY_SERVICE_AVAILABLE = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) ==
                    ConnectionResult.SUCCESS;
            Logger.debug(GPApplication.TAG,
                    "GPApplication.IS_GOOGLE_PLAY_SERVICE_AVAILABLE " + GPApplication.IS_GOOGLE_PLAY_SERVICE_AVAILABLE);
        }
        catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
}
