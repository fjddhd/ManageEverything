package fujingdong.com.manageeverything.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/10.
 * 日程设置页
 */
public class Setschedule extends BaseActivity {
    private MDatabaseHelper mDatabaseHelper=new MDatabaseHelper(this,"mDatabase",null,1);
    @Override
    public void initView() {
        super.initView();
        View view=View.inflate(Setschedule.this, R.layout.setschedule,rlBase);
        Button btn= (Button) view.findViewById(R.id.dianwo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.insert("title1","content1","50","100",null);
                mDatabaseHelper.insert("title1","content1","50","100",null);
                mDatabaseHelper.insert("title1","content1","50","100",null);
            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("增加日程");



    }
}
