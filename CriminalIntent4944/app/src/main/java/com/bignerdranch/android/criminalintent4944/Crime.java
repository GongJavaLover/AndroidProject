package com.bignerdranch.android.criminalintent4944;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 那么淡丶 on 2019/05/11.
 */

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private boolean mRequiresPolice;
    public boolean isRequiresPolice() {
        return mRequiresPolice;
    }
    public void setRequiresPolice(boolean requiresPolice) {
        mRequiresPolice = requiresPolice;
    }
    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();}
    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public Date getDate() {
        return mDate;
    }
    public void setDate(Date mDate) {
        this.mDate = mDate;
    }
    public boolean isSolved() {
        return mSolved;
    }
    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }


}
