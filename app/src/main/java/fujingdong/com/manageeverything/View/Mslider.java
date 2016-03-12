package fujingdong.com.manageeverything.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gc.materialdesign.views.Slider;

/**
 * Created by Administrator on 2016/3/12.
 */
public class Mslider extends Slider {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    public Mslider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }
}
