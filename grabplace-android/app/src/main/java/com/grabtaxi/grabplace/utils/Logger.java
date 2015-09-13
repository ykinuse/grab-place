package com.grabtaxi.grabplace.utils;

import android.util.Log;

/**
 * Util class that wraps around the standard android logger. This allows use to protect the logging based on debug or
 * live APK versions.
 */
public final class Logger
{
    // TODO: disable before release
    public static boolean DEBUG = true;

    private Logger()
    {
        // enforcing singleton
        super();
    }

    /**
     * Convenience method.
     *
     * @see Logger#debug(String, String, Throwable, boolean)
     */
    public static void debug(final String tag, final String message)
    {
        Logger.debug(tag, message, null, false);
    }

    /**
     * Abstraction of the standard android logging mechanics that can account for build/deploy target.
     *
     * @param tag       log message tag
     * @param message   log message
     * @param throwable cause of the problem
     * @param forceLog  flag, true will cause the message to always be logged, false will not be logged in production
     */
    public static void debug(final String tag, final String message, final Throwable throwable, final boolean forceLog)
    {
        if (forceLog || Logger.DEBUG)
        {
            Log.d(tag, message, throwable);
        }
    }

    /**
     * Convenience method.
     *
     * @see Logger#info(String, String, Throwable, boolean)
     */
    public static void info(final String tag, final String message)
    {
        Logger.info(tag, message, null, false);
    }

    /**
     * Abstraction of the standard android logging mechanics that can account for build/deploy target.
     *
     * @param tag       log message tag
     * @param message   log message
     * @param throwable cause of the problem
     * @param forceLog  flag, true will cause the message to always be logged, false will not be logged in production
     */
    public static void info(final String tag, final String message, final Throwable throwable, final boolean forceLog)
    {
        if (forceLog || Logger.DEBUG)
        {
            Log.i(tag, message, throwable);
        }
    }

    /**
     * Convenience method.
     *
     * @see Logger#warn(String, String, Throwable, boolean)
     */
    public static void warn(final String tag, final String message)
    {
        Logger.warn(tag, message, null, false);
    }

    /**
     * Abstraction of the standard android logging mechanics that can account for build/deploy target (Will log
     * exceptions to Crashlytics if in LIVE mode).
     *
     * @param tag       log message tag
     * @param message   log message
     * @param throwable cause of the problem
     * @param forceLog  flag, true will cause the message to always be logged, false will not be logged in production
     */
    public static void warn(final String tag, final String message, final Throwable throwable, final boolean forceLog)
    {
        if (forceLog || Logger.DEBUG)
        {
            Log.w(tag, message, throwable);
        }
    }

    /**
     * Convenience method.
     *
     * @see Logger#error(String, String, Throwable, boolean)
     */
    public static void error(final String tag, final String message)
    {
        Logger.error(tag, message, null, false);
    }

    /**
     * Abstraction of the standard android logging mechanics that can account for build/deploy target (Will log
     * exceptions to Crashlytics if in LIVE mode).
     *
     * @param tag       log message tag
     * @param message   log message
     * @param throwable cause of the problem
     * @param forceLog  flag, true will cause the message to always be logged, false will not be logged in production
     */
    public static void error(final String tag, final String message, final Throwable throwable, final boolean forceLog)
    {
        if (forceLog || Logger.DEBUG)
        {
            Log.e(tag, message, throwable);
        }
    }

    /**
     * Record breadcrumb/checkpoint.
     *
     * @param tag
     * @param message
     */
    public static void breadcrumb(final String tag, final String message)
    {
        // // send this to crittercism
        // Crittercism.leaveBreadcrumb(tag + ": " + message);

        // log it for debugging purposes
        Logger.debug(tag, message);
    }
}