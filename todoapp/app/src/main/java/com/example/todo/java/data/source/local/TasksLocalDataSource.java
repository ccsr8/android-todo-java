package com.example.todo.java.data.source.local;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.example.todo.java.data.Task;
import com.example.todo.java.data.source.TasksDataSource;
import com.example.todo.java.util.AppExecutors;

import java.util.List;

import static androidx.core.util.Preconditions.checkNotNull;

public class TasksLocalDataSource implements TasksDataSource {

    // region [private memebers]

    private static volatile TasksLocalDataSource INSTNACE;
    private TasksDao mTaskDao;
    private AppExecutors mAppExecutors;

    // endregion

    // region [constructor]

    private TasksLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull TasksDao tasksDao) {
        this.mAppExecutors = appExecutors;
        this.mTaskDao = tasksDao;
    }

    // endregion

    // region [public methods]

    public static TasksLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull TasksDao tasksDao) {
        if (INSTNACE == null) {
            synchronized (TasksLocalDataSource.class) {
                if (INSTNACE == null) {
                    INSTNACE = new TasksLocalDataSource(appExecutors, tasksDao);
                }
            }
        }

        return INSTNACE;
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTNACE = null;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Task> tasks = mTaskDao.getTasks();

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (tasks.isEmpty()) {
                            callback.onDataNotAvailable();
                        } else {
                            callback.onTasksLoaded(tasks);
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final Task task = mTaskDao.getTaskById(taskId);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (task != null) {
                            callback.onTaskLoaded(task);
                        } else {
                            callback.onDataNotAvailable();
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveTask(@NonNull final Task task) {
        checkNotNull(task);
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.insertTask(task);
            }
        };

        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void completeTask(@NonNull final Task task) {
        Runnable completeRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.updateCompleted(task.getId(), true);
            }
        };

        mAppExecutors.diskIO().execute(completeRunnable);
    }

    @Override
    public void completeTask(@NonNull String taskId) {
        // Not required
    }

    @Override
    public void activateTask(@NonNull final Task task) {
        Runnable activeRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.updateCompleted(task.getId(), false);
            }
        };

        mAppExecutors.diskIO().execute(activeRunnable);
    }

    @Override
    public void activateTask(@NonNull String taskId) {
        // Not required
    }

    @Override
    public void clearCompletedTasks() {
        Runnable clearTasksRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.deleteCompletedTasks();
            }
        };

        mAppExecutors.diskIO().execute(clearTasksRunnable);
    }

    @Override
    public void refreshTasks() {
        // Not required
    }

    @Override
    public void deleteAllTasks() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.deleteTasks();
            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    @Override
    public void deleteTask(@NonNull final String taskId) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                mTaskDao.deleteTaskById(taskId);
            }
        };

        mAppExecutors.diskIO().execute(deleteRunnable);
    }

    // endregion

}
