package fujingdong.com.manageeverything.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.gc.materialdesign.views.LayoutRipple;
import com.nineoldandroids.view.ViewHelper;

import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/11.
 * 应用设置页
 */
public class Setting extends BaseActivity implements View.OnClickListener{

    private AlertDialog clearAlertdialog;
    private View view;
    private MDatabaseHelper mDatabaseHelper=new MDatabaseHelper(this,"mDatabase",null,1);

    @Override
    public void initView() {
        super.initView();
        view = View.inflate(Setting.this, R.layout.setting, rlBase);
        LayoutRipple itemb1= (LayoutRipple) view.findViewById(R.id.itemButtons1);
        LayoutRipple itemb2= (LayoutRipple) view.findViewById(R.id.itemButtons2);
        LayoutRipple itemb3= (LayoutRipple) view.findViewById(R.id.itemButtons3);
        setOriginRiple(itemb1);
        setOriginRiple(itemb2);
        setOriginRiple(itemb3);
        itemb1.setOnClickListener(this);
        itemb2.setOnClickListener(this);
        itemb3.setOnClickListener(this);
//        rlBase.addView(view);
    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("控制台");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.itemButtons1:
                Intent i=new Intent(Setting.this,Schedule.class);
                startActivity(i);
//                finish();

                break;
            case R.id.itemButtons2:
                Intent ii=new Intent(Setting.this,Setschedule.class);
                startActivity(ii);
//                finish();
                break;
            case R.id.itemButtons3:
                showAlertDialog();
                break;

        }

    }

    /**
     * AlertDialog
     * @param
     */
    public void showAlertDialog(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        clearAlertdialog = builder.create();
        builder.setTitle("清除所有日程");
        builder.setMessage("该操作无法恢复，请再次确认是否需要清除所有日程");
        builder.setNegativeButton("先不清除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAlertdialog.dismiss();
            }
        });
        builder.setPositiveButton("清除吧！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //此处执行清除所有数据的操作！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！(要有finish和snackbar)
                mDatabaseHelper.todelete(true,null);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //什么也不做
                clearAlertdialog.dismiss();

            }
        });
        builder.show();
    }
    private void setOriginRiple(final LayoutRipple layoutRipple){

        layoutRipple.post(new Runnable() {

            @Override
            public void run() {
                View v = layoutRipple.getChildAt(0);
                layoutRipple.setxRippleOrigin(ViewHelper.getX(v)+v.getWidth()/2);
                layoutRipple.setyRippleOrigin(ViewHelper.getY(v)+v.getHeight()/2);

                layoutRipple.setRippleColor(Color.parseColor("#9fa8da"));

                layoutRipple.setRippleSpeed(40);
            }
        });

    }
}
