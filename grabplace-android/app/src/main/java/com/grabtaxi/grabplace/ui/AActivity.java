package com.grabtaxi.grabplace.ui;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class AActivity extends AppCompatActivity
{
    @Override
    protected void onResume()
    {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    protected boolean isLogeedIn()
    {
        final Profile profile = Profile.getCurrentProfile();
        return profile != null && !TextUtils.isEmpty(profile.getId());
    }
}
