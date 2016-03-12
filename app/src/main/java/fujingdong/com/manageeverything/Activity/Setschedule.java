package fujingdong.com.manageeverything.Activity;

import android.view.View;

import com.gc.materialdesign.views.ButtonFlat;

import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/10.
 * 日程设置页
 */
public class Setschedule extends BaseActivity {
    @Override
    public void initView() {
        super.initView();
        View view=View.inflate(Setschedule.this, R.layout.setschedule,rlBase);

    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("增加日程");



    }
}
