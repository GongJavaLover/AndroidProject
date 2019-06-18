package com.tjcu.gongxiaojie.sunset4944;


import android.support.v4.app.Fragment;

public class SunsetActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return SunsetFragment.newInstance();
    }

    }
