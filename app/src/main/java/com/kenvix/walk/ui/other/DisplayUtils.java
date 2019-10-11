package com.kenvix.walk.ui.other;

import android.view.View;

import com.kenvix.walk.ui.main.ExerciseFragment;
import com.kenvix.walk.ui.main.PersonalInformationFragment;

public class DisplayUtils {
    static {
        //System.loadLibrary("display-utils");
    }

    public static native void onPersonalInformationFragmentLoad(PersonalInformationFragment fragment);
    //public static native void onExerciseFragmentLoad(ExerciseFragment fragment, View view);
}
