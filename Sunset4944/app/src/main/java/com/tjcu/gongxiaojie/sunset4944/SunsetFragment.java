package com.tjcu.gongxiaojie.sunset4944;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;


public class SunsetFragment extends Fragment {
    private View mSceneView;
    private View mSunView;
    private View mSkyView;
    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private boolean isSunDown = true;
    private ObjectAnimator sunsetSkyAnimator;
    private ObjectAnimator nightSkyAnimator;
    private float sunYFirstStart;
    private float sunYFirstEnd;
    private boolean isFirstClick = true;
    private ObjectAnimator downAnimatior;
    private AnimatorSet downAnimatorSet;
    private ObjectAnimator upAnimatior;
    private AnimatorSet upAnimatorSet;
    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);
        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);
        mBlueSkyColor = ContextCompat.getColor(getActivity(), R.color.blue_sky);
        mSunsetSkyColor = ContextCompat.getColor(getActivity(), R.color.sunset_sky);
        initSkyAnimation();
        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstClick) {
                    sunYFirstStart = mSunView.getTop();
                    sunYFirstEnd = mSkyView.getHeight();
                    initUpDownAnimation();
                }
                isFirstClick = false;
                if(!downAnimatorSet.isRunning() && !upAnimatorSet.isRunning()){
                    if (isSunDown) {
                        downAnimatorSet.start();
                    } else {
                        upAnimatorSet.start();
                    }
                    isSunDown = !isSunDown;
                }
            }
        });
        return view;
    }
    private void initUpDownAnimation() {
        downAnimatior = ObjectAnimator.ofFloat(mSunView, "y", sunYFirstStart, sunYFirstEnd).setDuration(3000);
        downAnimatior.setInterpolator(new AccelerateInterpolator());
        downAnimatorSet = new AnimatorSet();
        downAnimatorSet.play(downAnimatior).with(sunsetSkyAnimator);
        upAnimatior = ObjectAnimator.ofFloat(mSunView, "y", sunYFirstEnd, sunYFirstStart).setDuration(3000);


        upAnimatior.setInterpolator(new AccelerateInterpolator());
        upAnimatorSet = new AnimatorSet();
        upAnimatorSet.play(upAnimatior).with(nightSkyAnimator);


    }

    private void initSkyAnimation() {
        sunsetSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor).setDuration(3000);
        sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());
        nightSkyAnimator = ObjectAnimator.ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mBlueSkyColor).setDuration(3000);
        nightSkyAnimator.setEvaluator(new ArgbEvaluator());
    }
}



