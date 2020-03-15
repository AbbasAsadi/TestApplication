package com.example.testapplication.customView;

import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;

import com.example.testapplication.model.User;

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
    private User mUser = new User();
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
            } else {
                createUser(customView);
            }
        }
        return true;
    }

    private void createUser(InputCustomView customView) {

        String text = customView.getEditText().getText().toString();
        switch (customView.getTextType()) {
            case InputCustomView.NAME:
                mUser.setName(text);
                break;
            case InputCustomView.EMAIL:
                mUser.setEmail(text);
                break;
            case InputCustomView.PHONE_NUMBER:
                mUser.setPhoneNumber(text);
                break;
            case InputCustomView.GENERAL:

                break;
        }

    }

}
