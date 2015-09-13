package com.grabtaxi.grabplace.services;

import android.content.Context;

import com.grabtaxi.grabplace.BuildConfig;
import com.grabtaxi.grabplace.Constant;

import retrofit.RestAdapter;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class GrabPlaceAPI
{
    protected static final String        TAG               = GrabPlaceAPI.class.getSimpleName();
    private static final   GrabPlaceAPI  INSTANCE          = new GrabPlaceAPI();
    // Instance Variables
    private final          RestAdapter   mRestAdapter      = GrabPlaceAPI.getRestAdapter();
    private                IGrabPlaceAPI mGrabMoneyService = this.mRestAdapter.create(IGrabPlaceAPI.class);
    private Context mContext;

    private GrabPlaceAPI()
    {
        super();
    }

    public static GrabPlaceAPI getInstance()
    {
        return GrabPlaceAPI.INSTANCE;
    }

    private static RestAdapter getRestAdapter()
    {
        final RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder().setEndpoint(Constant.GRABPLACE_API_ENDPOINT);

        restAdapterBuilder.setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);

        return restAdapterBuilder.build();
    }

    public void initialize(final Context context)
    {
        this.mContext = context;
    }

}
