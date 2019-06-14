package com.example.todo.java.data.source;

import androidx.annotation.NonNull;

import com.example.todo.java.data.Task;

import java.util.LinkedHashMap;
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

    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,
                                              TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }

        return INSTANCE;
    }

    // endregion

    // region [public methods]

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback) {

    }

    @Override
    public void getTasks(@NonNull LoadTasksCallback callback) {

    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull Task task) {

    }

    @Override
    public void completeTask(@NonNull String taskId) {

    }

    @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {
        this.mTasksRemoteDataSource.deleteAllTasks();
        this.mTasksLocalDataSource.deleteAllTasks();

        if (this.mCachedTasks == null) {
            this.mCachedTasks = new LinkedHashMap<>();
        }

        this.mCachedTasks.clear();
    }

    @Override
    public void deleteTask(@NonNull String taskId) {
        this.mTasksRemoteDataSource.deleteTask(checkNotNull(taskId));
        this.mTasksLocalDataSource.deleteTask(checkNotNull(taskId));
    }

    // endregion
}
