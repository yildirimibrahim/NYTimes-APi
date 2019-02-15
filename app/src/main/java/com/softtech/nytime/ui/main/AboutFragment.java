package com.softtech.nytime.ui.main;

import android.support.v4.app.Fragment;

import com.softtech.nytime.R;
import com.softtech.nytime.ui.BaseFragment;

/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class AboutFragment extends BaseFragment {

    public static Fragment create() {
        return new AboutFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

}
