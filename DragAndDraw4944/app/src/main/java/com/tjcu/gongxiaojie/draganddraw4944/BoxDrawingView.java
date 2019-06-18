package com.tjcu.gongxiaojie.draganddraw4944;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View{
    private static final String TAG = "BoxDrawingView";
    private Box mCurrentBox;
    private static List<Box> mBoxes = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context){
        this(context,null);
    }
    public BoxDrawingView(Context context, AttributeSet attrs){
        super(context,attrs);
        //给盒子上色(半透明红色)
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);
        //背景上白色
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
        DragAndDrawFragment.setBoxDrawingView(this);
    }
    public boolean onTouchEvent(MotionEvent event){
        PointF current = new PointF(event.getX(),event.getY());
        String action = "";

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                action = "手触摸屏幕";
                mCurrentBox = new Box(current);
                mBoxes.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                action = "手在屏幕上滑动";
                if(mCurrentBox != null){
                    mCurrentBox.setCurrent(current);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                action = "手离开了屏幕";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "父视图获取触摸事件";
                mCurrentBox = null;
                break;
        }
        Log.i(TAG,action + " at x=" + current.x + " , y=" + current.y);

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);
        for (Box box : mBoxes){
            float left = Math.min(box.getOrigin().x,box.getCurrent().x);
            float right = Math.max(box.getOrigin().x,box.getCurrent().x);
            float top = Math.min(box.getOrigin().y,box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y,box.getCurrent().y);
            canvas.drawRect(left,top,right,bottom,mBoxPaint);
        }
    }
    public static List<Box> getBoxes() {
        return mBoxes;
    }

    public static void setBoxes(List<Box> boxes) {
        mBoxes = boxes;
    }
}
