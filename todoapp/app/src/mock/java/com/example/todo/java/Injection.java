package com.example.todo.java;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.todo.java.data.FakeTasksRemoteDataSource;
import com.example.todo.java.data.source.TasksRepository;
import com.example.todo.java.data.source.local.ToDoDatabase;

import static androidx.core.util.Preconditions.checkNotNull;

public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        ToDoDatabase toDoDatabase = ToDoDatabase.getInstance(context);

        // TODO: Injection
        return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance)
    }

}
