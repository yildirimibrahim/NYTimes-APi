package com.softtech.nytime.ui;

import android.support.v4.app.Fragment;

import com.softtech.nytime.R;

public class AboutFragment extends BaseFragment {

    public static Fragment create() {
        return new AboutFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

}
