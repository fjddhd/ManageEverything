package fujingdong.com.manageeverything.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/3/12.
 */
public class MRecyclerview extends RecyclerView {

    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public MRecyclerview(Context context) {
        super(context);
    }

    public MRecyclerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MRecyclerview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
