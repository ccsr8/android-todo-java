package com.example.todo.java.data.source.local;

import androidx.annotation.NonNull;

import com.example.todo.java.data.source.TasksDataSource;
import com.example.todo.java.util.AppExecutors;

public class TasksLocalDataSource implements TasksDataSource {

    // region [private memebers]

    private static volatile TasksLocalDataSource INSTNACE;
    private TasksDao mTaskDao;
    private AppExecutors mAppExecutors;

    // endregion

    // region [constructor]

    private TasksLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull TasksDao tasksDao) {
        this.mAppExecutors = appExecutors;
        this.mTaskDao = tasksDao;
    }

    // endregion

    // region [public methods]

    public static TasksLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull TasksDao tasksDao) {
        if (INSTNACE == null) {
            synchronized (TasksLocalDataSource.class) {
                if (INSTNACE == null) {
                    INSTNACE = new TasksLocalDataSource(appExecutors, tasksDao);
                }
            }
        }

        return INSTNACE;
    }

    // TODO getTask

    // endregion

}
