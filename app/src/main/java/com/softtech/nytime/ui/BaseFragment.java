package com.softtech.nytime.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.softtech.nytime.R;
import timber.log.Timber;

/**
 * MaxiBilgi
 * Created by SoftTech Garage on 15.02.2019.
 */
public class BaseFragment
        extends Fragment {

    private ProgressDialog dialog;

    @Override
    public void onPause() {
        Timber.i("onPause");
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.i("onCreateView");
        return inflater.inflate(getLayoutId(), container, false);
    }

    public int getLayoutId() {
        return 0;
    }

    public void showProgress() {
        if (dialog == null) {
            dialog = new ProgressDialog(getActivity());
        }
        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.show();
    }

    public void dismissProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


}
