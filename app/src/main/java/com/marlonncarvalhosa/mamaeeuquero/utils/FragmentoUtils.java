package com.marlonncarvalhosa.mamaeeuquero.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.marlonncarvalhosa.mamaeeuquero.R;

public class FragmentoUtils {

    public static void replace(FragmentActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmento, fragment).commit();
    }

}
