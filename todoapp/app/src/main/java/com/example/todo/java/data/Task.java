package com.example.todo.java.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public final class Task {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private final String mId;

    @Nullable
    @ColumnInfo(name = "title")
    private final String mTitle;


    @Nullable
    @ColumnInfo(name = "description")
    private final String mDescription;


    @ColumnInfo(name = "completed")
    private final boolean mCompleted;


    @Ignore
    public Task(@Nullable String title, @Nullable String description){

    }

}
