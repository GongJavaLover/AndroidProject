package com.bignerdranch.android.criminalintent4944;
import android.support.v4.app.Fragment;
/**
 * Created by 那么淡丶 on 2019/05/12.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
       return new CrimeListFragment();
    }
}
