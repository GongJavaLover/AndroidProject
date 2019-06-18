package com.android.app.geoquiz;

/**
 * Created by 那么淡丶 on 2019/04/21.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public Question(int textResId, Boolean answerTrue){
        mTextResId=textResId;
        mAnswerTrue=answerTrue;

    }
}
