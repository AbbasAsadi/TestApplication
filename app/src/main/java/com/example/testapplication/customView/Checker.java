package com.example.testapplication.customView;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private final String TAG = "Checker";
    private NestedScrollView mScrollView;
    private ViewGroup mRootView;
    private List<View> mChildView = new ArrayList<>();

    public Checker(NestedScrollView scrollView, ViewGroup rootView) {
        mScrollView = scrollView;
        mRootView = rootView;
        IsValid();
    }

    public boolean IsValid() {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            View childAt = mRootView.getChildAt(i);
            if (childAt instanceof InputCustomView) {
                mChildView.add(childAt);
                InputCustomView customView = (InputCustomView) childAt;
                if (!customView.isMyTextValid()) {
                    mScrollView.smoothScrollTo(0, customView.getTop() - 24);
                    return false;
                }
            }
        }
        return true;
    }

}
