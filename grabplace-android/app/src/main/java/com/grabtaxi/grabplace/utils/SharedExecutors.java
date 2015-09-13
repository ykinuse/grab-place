package com.grabtaxi.grabplace.utils;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SharedExecutors
{
    private static final String                                     TAG                = SharedExecutors.class.getSimpleName();
    private static final SharedExecutors                            INSTANCE           = new SharedExecutors();
    private final        ExecutorService                            CACHED_THREAD_POOL = Executors.newCachedThreadPool();
    private static final HashMap<SharedExecutorsIdentifier, Future> THREAD_MAP         = new HashMap<SharedExecutorsIdentifier, Future>();

    private SharedExecutors()
    {
        // Ensure Singleton
        super();
    }

    public static synchronized SharedExecutors getInstance()
    {
        return SharedExecutors.INSTANCE;
    }

    public Future execute(final Runnable runnable, final SharedExecutorsIdentifier tag)
    {
        synchronized (SharedExecutors.THREAD_MAP)
        {
            Future mFuture = null;

            if (tag != null && SharedExecutors.THREAD_MAP.containsKey(tag))
            {
                Logger.debug(SharedExecutors.TAG, "Returning running thread for " + tag.toString());
                return SharedExecutors.THREAD_MAP.get(tag);
            }

            if (runnable != null)
            {
                if (tag != null)
                {
                    Logger.debug(SharedExecutors.TAG, "Saving running thread for " + tag.toString());
                    SharedExecutors.THREAD_MAP.put(tag, this.CACHED_THREAD_POOL.submit(runnable));
                    mFuture = SharedExecutors.THREAD_MAP.get(tag);
                }
                else
                {
                    mFuture = this.CACHED_THREAD_POOL.submit(runnable);
                }
            }

            return mFuture;
        }
    }

    public boolean stop(final SharedExecutorsIdentifier tag)
    {
        synchronized (SharedExecutors.THREAD_MAP)
        {
            if (tag == null)
            {
                Logger.debug(SharedExecutors.TAG, "Ignored stop thread without tag");
                return false;
            }

            final Future future = SharedExecutors.THREAD_MAP.remove(tag);
            if (future != null)
            {
                final boolean result = future.cancel(true);
                Logger.debug(SharedExecutors.TAG, "Stop thread for " + tag.toString() + " " + result);
                return result;
            }

            return false;
        }
    }

    public void reset()
    {
        synchronized (SharedExecutors.THREAD_MAP)
        {
            SharedExecutors.THREAD_MAP.clear();
        }
    }

    public boolean isTagExistInMap(final SharedExecutorsIdentifier tag)
    {
        synchronized (SharedExecutors.THREAD_MAP)
        {
            if (tag == null)
            {
                return false;
            }
            final boolean result = SharedExecutors.THREAD_MAP.containsKey(tag);
            Logger.debug(SharedExecutors.TAG, "isTagExistInMap " + tag.toString() + " result " + result);
            return result;
        }
    }

    public static abstract class SharedExecutorsRunnable implements Runnable
    {
        protected final SharedExecutorsIdentifier tag;

        public SharedExecutorsRunnable(final SharedExecutorsIdentifier tag)
        {
            this.tag = tag;
        }

        @Override
        public void run()
        {
            try
            {
                onRunning();
            }
            finally
            {
                final boolean stopped = SharedExecutors.getInstance().stop(this.tag);
                Logger.debug(SharedExecutors.TAG, "Finally stop " + tag.toString() + " result " + stopped);
            }
        }

        protected abstract void onRunning();
    }

    public static class SharedExecutorsIdentifier
    {
        public String firstParameter;
        public String secondParameter;

        public SharedExecutorsIdentifier(final Class firstParameter, final String secondParameter)
        {
            this.firstParameter = firstParameter.getSimpleName();
            this.secondParameter = secondParameter;
        }

        @Override
        public String toString()
        {
            return firstParameter + " " + secondParameter;
        }

        @Override
        public boolean equals(final Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o == null || getClass() != o.getClass())
            {
                return false;
            }

            final SharedExecutorsIdentifier that = (SharedExecutorsIdentifier) o;

            if (firstParameter != null ? !firstParameter.equals(that.firstParameter) : that.firstParameter != null)
            {
                return false;
            }
            return !(secondParameter != null ? !secondParameter.equals(that.secondParameter) : that.secondParameter != null);

        }

        @Override
        public int hashCode()
        {
            int result = firstParameter != null ? firstParameter.hashCode() : 0;
            result = 31 * result + (secondParameter != null ? secondParameter.hashCode() : 0);
            return result;
        }
    }
}