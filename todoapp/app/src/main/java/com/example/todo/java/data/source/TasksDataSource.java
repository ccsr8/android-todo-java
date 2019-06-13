package com.example.todo.java.data.source;

import com.example.todo.java.data.Task;

import java.util.List;

public interface TasksDataSource {

    interface LoadTasksCallback {
        void onTasksLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

}
