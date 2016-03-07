package com.example.dam.testanimation.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;

/**
 * Created by dam on 4/3/16.
 */
public class MyAnimation {

    private static boolean initStatus = false;

    public static int ShortAnimationDuration;
    public static int MediumAnimationDuration;
    public static int LongAnimationDuration;

    private static void init(Context context) {
        if (!initStatus) {
            ShortAnimationDuration = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
            MediumAnimationDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
            LongAnimationDuration = context.getResources().getInteger(android.R.integer.config_longAnimTime);
            initStatus = true;
        }
    }

    public static void showView(Context context, View view) {

        init(context);

        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .alpha(1f)
                .setDuration(ShortAnimationDuration)
                .setListener(null);
    }

    public static void hideView(Context context, View view){
        init(context);

        view.animate()
                .alpha(0f)
                .setDuration(ShortAnimationDuration)
                .setListener(new HideAnimatorListenerAdapter(view));
    }

    private static class HideAnimatorListenerAdapter extends AnimatorListenerAdapter{
        private View view;
        HideAnimatorListenerAdapter(View view){
            this.view = view;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            view.setVisibility(View.GONE);
        }
    }

}
