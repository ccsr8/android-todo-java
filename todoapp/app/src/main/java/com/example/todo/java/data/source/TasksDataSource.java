package com.example.todo.java.data.source;

import androidx.annotation.NonNull;

import com.example.todo.java.data.Task;

import java.util.List;

public interface TasksDataSource {

    void getTasks(@NonNull LoadTasksCallback callback);

    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);

    void saveTask(@NonNull Task task);

    void completeTask(@NonNull Task task);

    void completeTask(@NonNull String taskId);

    void activateTask(@NonNull Task task);

    void activateTask(@NonNull String taskId);

    void clearCompletedTasks();

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(@NonNull String taskId);

    interface LoadTasksCallback {
        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {
        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }


}
