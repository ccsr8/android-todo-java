package com.example.todo.java.tasks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.todo.java.R;

public class TaskActivity extends AppCompatActivity {

    //region [public members]

    public static final String TASKS_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";
    private DrawerLayout mDrawerLayout;
    //private TaskViewModel mViewModel;


    //endregion

    //region [private members]

    //endregion

    //region [event handler]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    //endregion
}
