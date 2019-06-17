package com.example.todo.java.data;

import com.example.todo.java.data.source.TasksDataSource;

import java.util.LinkedHashMap;
import java.util.Map;

public class FakeTasksRemoteDataSource implements TasksDataSource {

    // region [private members]

    private static final Map<String, Task> TASKS_SERVICE_DATA = new LinkedHashMap<>();
    private static FakeTasksRemoteDataSource INSTANCE;

    // endregion

    // region [constructor]

    private FakeTasksRemoteDataSource() {
    }


    // endregion

    // region [public methods]

    public static FakeTasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeTasksRemoteDataSource();
        }

        return INSTANCE;
    }

    // TODO: Override getTasks

    // endregion

}
