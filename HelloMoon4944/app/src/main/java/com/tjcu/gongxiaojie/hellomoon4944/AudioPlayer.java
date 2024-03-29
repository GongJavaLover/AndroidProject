package com.tjcu.gongxiaojie.hellomoon4944;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
    private MediaPlayer mPlayer;
    private boolean isPause;
    public void stop(){
       if (mPlayer != null){

           mPlayer.release();
           mPlayer=null;
           isPause = false;

       }
    }
    public void play(Context c) {
        if (isPause) {
            mPlayer.start();
        } else {
            stop();
            mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mPlayer.start();
                }
            });
            mPlayer.start();
        }
        isPause = false;
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
            isPause = true;
        }
    }
    public boolean isPlaying() {
        if (mPlayer != null)
            return mPlayer.isPlaying();
        else
            return false;
    }
}

