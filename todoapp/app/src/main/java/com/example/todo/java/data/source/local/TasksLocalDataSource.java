package com.example.todo.java.data.source.local;

import com.example.todo.java.data.source.TasksDataSource;

public class TasksLocalDataSource implements TasksDataSource {

    // region [private memebers]

    private static volatile TasksLocalDataSource INSTNACE;
    private TasksDao mTaskDao;
    // TODO Create AppExecutors Class
    private AppExecutors mAppExecutors;


    // endregion

}
