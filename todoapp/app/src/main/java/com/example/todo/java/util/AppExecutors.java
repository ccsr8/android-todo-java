package com.example.todo.java.util;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.VisibleForTesting;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    // region [private members]

    private static final int THREAD_COUNT = 3;
    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor mainThread;

    // endregion

    // region [constructor]

    @VisibleForTesting
    AppExecutors(Executor diskIO, Executor networkIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.networkIO = networkIO;
        this.mainThread = mainThread;
    }

    public AppExecutors() {
        this(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
    }

    // endregion

    // region [public methods]

    public Executor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor mainThread() {
        return mainThread;
    }

    // endregion

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            this.mainThreadHandler.post(runnable);
        }
    }

}
