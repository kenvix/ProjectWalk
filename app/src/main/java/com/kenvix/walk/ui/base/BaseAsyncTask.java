package com.kenvix.walk.ui.base;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.kenvix.utils.log.LogUtilsKt;
import com.kenvix.utils.log.Logging;

import java.util.function.Consumer;

public abstract class BaseAsyncTask<T, U, X> extends AsyncTask<T, U, X> implements Logging {
    private Exception exception = null;
    private String taskName;
    @Nullable
    private Consumer<Exception> onExceptionCallback;

    /**
     * Run task default implement
     * @param ts args
     * @return X if success, null if failed
     */
    @Override
    @SafeVarargs
    protected final @Nullable X doInBackground(T... ts) {
        try {
            return doTask(ts);
        } catch (Exception e) {
            setException(e);
            onException(e);
            return null;
        }
    }

    protected abstract X doTask(T... ts) throws Exception;
    protected void onException(Exception exception) {
        LogUtilsKt.warning(getLogger(), exception, "Task failed");

        if (onExceptionCallback != null)
            onExceptionCallback.accept(exception);
    }

    public Exception getException() {
        return exception;
    }

    public Consumer<Exception> getOnExceptionCallback() {
        return onExceptionCallback;
    }

    public void setOnExceptionCallback(@Nullable Consumer<Exception> onExceptionCallback) {
        this.onExceptionCallback = onExceptionCallback;
    }

    protected void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String getLogTag() {
        return taskName == null ? taskName = this.getClass().getSimpleName() : taskName;
    }

    public boolean isExceptionThrown() {
        return exception != null;
    }
}

