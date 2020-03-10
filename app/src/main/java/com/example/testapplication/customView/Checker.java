package com.example.testapplication.customView;

import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * this class check validation of all {@link InputCustomView} that rootView contain
 *
 * @author Abbas Asadi GitHub:AbbasAsadi
 * @since API 29
 */
public class Checker {
    private final String TAG = "Checker";
    private NestedScrollView mScrollView;
    private ViewGroup mRootView;
    private List<InputCustomView> mCustomViewList = new ArrayList<>();

    /**
     * constructor of Checker class
     *
     * @param scrollView if a {@link InputCustomView} is invalid than we use {@param scrollView} to scroll to that
     * @param rootView   our root view that contains our {@link InputCustomView}
     */
    public Checker(NestedScrollView scrollView, ViewGroup rootView) {
        mScrollView = scrollView;
        mRootView = rootView;
        getChildViews();
    }

    /**
     * this method get childes of rootView
     */
    private void getChildViews() {
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            if (mRootView.getChildAt(i) instanceof InputCustomView) {
                InputCustomView customView = (InputCustomView) mRootView.getChildAt(i);
                mCustomViewList.add(customView);
            }
        }
    }

    /**
     * with isMyTextValid() method we check validation of childViews
     *
     * @return true if our {@link InputCustomView} has valid input
     */
    public boolean IsValid() {
        for (InputCustomView customView : mCustomViewList) {
            if (!customView.isMyTextValid()) {
                mScrollView.smoothScrollTo(0, customView.getTop() - 24);
                return false;
            }
        }
        return true;
    }

}
