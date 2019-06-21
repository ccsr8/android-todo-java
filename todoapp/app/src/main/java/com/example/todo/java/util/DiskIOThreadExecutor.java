package com.example.todo.java.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIOThreadExecutor implements Executor {
    private final Executor mDiskIO;

    public DiskIOThreadExecutor() {
        this.mDiskIO = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        this.mDiskIO.execute(runnable);
    }
}
