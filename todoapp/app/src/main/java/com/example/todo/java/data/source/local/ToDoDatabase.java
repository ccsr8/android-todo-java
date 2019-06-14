package com.example.todo.java.data.source.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class ToDoDatabase extends RoomDatabase {

    // region [private members]

    private static final Object sLock = new Object();
    private static ToDoDatabase INSTANCE;

    // endregion

    // region [public members]

    public static ToDoDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ToDoDatabase.class,
                        "Tasks.db")
                        .build();
            }

            return INSTANCE;
        }
    }

    // endregion

    // region [public methods]

    public abstract TasksDao tasksDao();

// endregion

}
