package com.example.todo.java.data.source.local;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todo.java.data.Task;

import java.util.List;

public interface TasksDao {
    @Query("SELECT * FROM Tasks")
    List<Task> getTasks();

    @Query("SELECT * FROM Tasks WHERE entryid = :taskId")
    Task getTaskById(String taskId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Update
    int updateTask(Task task);

    @Query("UPDATE Tasks SET completed = :completed WHERE entryid = :taskId")
    void updateCompleted(String taskId, boolean completed);

    @Query("DELETE FROM Task WHERE entryid = :taskId")
    int deleteTaskById(String taskId);

    @Query("DELETE FROM Tasks")
    void deleteTasks();

    @Query("DELETE FROM Tasks WHERE completed = 1")
    int deleteCompletedTasks();
}
