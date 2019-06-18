package com.tjcu.gongxiaojie.draganddraw4944;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DragAndDrawFragment extends Fragment {
    private static BoxDrawingView sBoxDrawingView;
    private Box mBox = new Box();
    public static DragAndDrawFragment newInstance(){
        return new DragAndDrawFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_drag_and_draw,container,false);
        if (savedInstanceState != null){
            for(int i = 0;i<savedInstanceState.size();i++){
                PointF origin = new PointF();
                PointF current = new PointF();
                origin.x = savedInstanceState.getInt("originY"+i);
                origin.y = savedInstanceState.getInt("originX"+i);
                current.x = savedInstanceState.getInt("currentY"+i);
                current.y = savedInstanceState.getInt("currentX"+i);
                mBox.setOrigin(origin);
                mBox.setCurrent(current);
                sBoxDrawingView.getBoxes().add(mBox);
            }
        }
        return view;
    }

    public static void setBoxDrawingView(BoxDrawingView mBoxDrawingView){
        sBoxDrawingView = mBoxDrawingView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i = 0; i < sBoxDrawingView.getBoxes().size(); i++){
            mBox = sBoxDrawingView.getBoxes().get(i);
            outState.putInt("originX"+i,(int) mBox.getOrigin().x);
            outState.putInt("originY"+i, (int) mBox.getOrigin().y);
            outState.putInt("currentX"+i, (int) mBox.getCurrent().x);
            outState.putInt("currentY"+i, (int) mBox.getCurrent().y);
        }
    }
}
