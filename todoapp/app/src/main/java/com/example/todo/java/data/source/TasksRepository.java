package com.example.todo.java.data.source;

import androidx.annotation.NonNull;

import com.example.todo.java.data.Task;

import java.util.Map;

import static androidx.core.util.Preconditions.checkNotNull;

public class TasksRepository implements TasksDataSource {

    // region [private members]

    private static TasksRepository INSTANCE = null;
    private final TasksDataSource mTasksRemoteDataSource;
    private final TasksDataSource mTasksLocalDataSource;

    Map<String, Task> mCachedTasks;
    boolean mCacheIsDirty = false;

    // endregion

    // region [constructor]

    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,
                            @NonNull TasksDataSource tasksLocalDataSource) {
        this.mTasksRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        this.mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    // endregion

    // region [public methods]

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    //TODO: getTasks

    // endregion
}
