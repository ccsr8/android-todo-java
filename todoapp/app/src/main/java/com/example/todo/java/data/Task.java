package com.example.todo.java.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "tasks")
public final class Task {

    // region [private members]

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

    // endregion

    // region [constructor]

    @Ignore
    public Task(@Nullable String title,
                @Nullable String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }

    @Ignore
    public Task(@Nullable String title,
                @Nullable String description,
                @NonNull String id) {
        this(title, description, id, false);
    }

    @Ignore
    public Task(@Nullable String title,
                @Nullable String description,
                boolean completed) {
        this(title, description, UUID.randomUUID().toString(), completed);
    }

    public Task(@Nullable String title,
                @Nullable String description,
                @NonNull String id,
                boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    // endregion

    // region [properties]

    public boolean isCompleted() {
        return mCompleted;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    // endregion
}
