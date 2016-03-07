package com.example.dam.testanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dam.testanimation.util.MyAnimation;

public class CrossFadeActivity extends AppCompatActivity {

    private ProgressBar bar = null;
    private TextView textView = null;
    private boolean status = true;
    private View loadView ;
    private View hideView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        textView.setVisibility(View.GONE);

    }

    public void fadeAction(View view) {

        loadView = status ? textView : bar ;
        hideView = status ? bar : textView ;
        status = !status;

        MyAnimation.showView(this, loadView);
        MyAnimation.hideView(this, hideView);

    }
}
