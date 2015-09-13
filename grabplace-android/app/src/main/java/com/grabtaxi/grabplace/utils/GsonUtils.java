package com.grabtaxi.grabplace.utils;

import com.google.gson.Gson;

/**
 * Created by ykinuse on 13/09/2015.
 */
public class GsonUtils
{
    private static final Gson GSON = new Gson();

    private GsonUtils()
    {
        // Ensure Singleton
    }

    public static Gson getGson()
    {
        return GsonUtils.GSON;
    }
}