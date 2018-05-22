package com.marlonncarvalhosa.mamaeeuquero.utils;

import android.support.v4.app.FragmentActivity;

import com.marlonncarvalhosa.mamaeeuquero.R;
import com.marlonncarvalhosa.mamaeeuquero.fragments.DescricaoFragment;

public class FragmentoUtils {

    public static void replace(FragmentActivity activity, DescricaoFragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmento, fragment).commit();
    }

}
