package com.softtech.nytime.core.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.softtech.nytime.NYTimesApp;
import java.util.List;
import timber.log.Timber;

public class NYTimesUtils {

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static int getColor(@ColorRes int resourceId) {
        return ContextCompat.getColor(NYTimesApp.getContext(), resourceId);
    }

    public static Intent openBrowser(String url) {
        try {
           return new Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (ActivityNotFoundException e) {
            Timber.e(e);
            return null;
        }
    }

}