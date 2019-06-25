package com.example.todo.java.tasks;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.todo.java.Injection;
import com.example.todo.java.R;
import com.example.todo.java.ViewModelHolder;
import com.example.todo.java.util.ActivityUtils;
import com.google.android.material.navigation.NavigationView;

public class TasksActivity extends AppCompatActivity {

    //region [public members]

    public static final String TASKS_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";

    //endregion

    //region [private members]

    private DrawerLayout mDrawerLayout;
    private TasksViewModel mViewModel;

    //endregion

    //region [event handler]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_tasks);

        this.setupToolbar();
        this.setupNavigationDrawer();

        TasksFragment tasksFragment = this.findOrCreateViewFragment();

        mViewModel = this.findOrCreateViewModel();
    }

    private TasksViewModel findOrCreateViewModel() {
        ViewModelHolder<TasksViewModel> retainedViewModel =
                (ViewModelHolder<TasksViewModel>) super.getSupportFragmentManager()
                        .findFragmentByTag(TASKS_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            return retainedViewModel.getViewModel();
        } else {
            //TasksViewModel viewModel = new TasksViewModel(
            //        Injection.provideTasksRepository(getApplicationContext()),
             //       getApplicationContext());

            TasksViewModel viewModel = new TasksViewModel(
                    Injection.provideTasksRepository(getApplicationContext()),
                    getApplicationContext());

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    TASKS_VIEWMODEL_TAG);

            return viewModel;
        }
    }

    //endregion

    // region [private methods]

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.list_navigation_menu_item:
                                break;
                            case R.id.statistics_navigation_menu_item:
                                // new activity
                                break;
                            default:
                                break;
                        }

                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private TasksFragment findOrCreateViewFragment() {
        TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (tasksFragment == null) {
            tasksFragment = TasksFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), tasksFragment, R.id.contentFrame);
        }

        return tasksFragment;
    }

    // endregion
}
