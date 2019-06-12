package com.example.todo.java;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewModelHolder<VM> extends Fragment {
    //region [private members]

    private VM mViewModel;

    //endregion

    // region [constructor]

    public ViewModelHolder() {
    }

    // endregion

    // region [public methods]

    public static <M> ViewModelHolder createContainer(@NonNull M viewModel) {
        ViewModelHolder<M> viewModelContainer = new ViewModelHolder<>();
        viewModelContainer.setViewModel(viewModel);

        return viewModelContainer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRetainInstance(true);
    }

    // endregion

    // region [properties]

    @Nullable
    public VM getViewModel() {
        return this.mViewModel;
    }

    public void setViewModel(VM mViewModel) {
        this.mViewModel = mViewModel;
    }

    // endregion
}

