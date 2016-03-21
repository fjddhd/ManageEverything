package fujingdong.com.manageeverything.Activity;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        View view=View.inflate(Setschedule.this, R.layout.set_schedule,rlBase);
        TextInputLayout til1= (TextInputLayout) view.findViewById(R.id.til1);
        til1.setHint("请输入标题");
        EditText editText1 = til1.getEditText();
        Editable text1 = editText1.getText();
//

    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("增加日程");



    }
}
