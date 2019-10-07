package com.kenvix.walk.ui.base;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.kenvix.utils.log.LogUtilsKt;
import com.kenvix.utils.log.Logging;

import java.util.function.Consumer;

/**
 * 基 AsyncTask
 * @param <T>
 * @param <U>
 * @param <X>
 */
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

    /**
     * 所要执行的任务
     * @param ts 任务的参数列表
     * @return X 任务结果
     * @throws Exception 任务执行过程中抛出的异常
     */
    protected abstract X doTask(T... ts) throws Exception;
    protected void onException(Exception exception) {
        LogUtilsKt.warning(getLogger(), exception, "Task failed");

        if (onExceptionCallback != null)
            onExceptionCallback.accept(exception);
    }

    /**
     * 获取任务执行过程中抛出的异常
     * @return null 为无异常
     */
    public Exception getException() {
        return exception;
    }

    /**
     * 获取任务发生异常时的回调
     * @return
     */
    public Consumer<Exception> getOnExceptionCallback() {
        return onExceptionCallback;
    }

    /**
     * 设置任务发生异常时的回调
     * @param onExceptionCallback
     */
    public void setOnExceptionCallback(@Nullable Consumer<Exception> onExceptionCallback) {
        this.onExceptionCallback = onExceptionCallback;
    }

    /**
     * 标记任务处于异常状态
     * @param exception
     */
    protected void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String getLogTag() {
        return taskName == null ? taskName = this.getClass().getSimpleName() : taskName;
    }

    /**
     * 判断任务执行过程中是否抛出异常
     * @return
     */
    public boolean isExceptionThrown() {
        return exception != null;
    }
}

