package fujingdong.com.manageeverything.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.gc.materialdesign.views.LayoutRipple;
import com.nineoldandroids.view.ViewHelper;

import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;
import fujingdong.com.manageeverything.Utils.PrefUtils;

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
    protected void onResume() {
        super.onResume();
//        System.out.println("resume");
        PrefUtils.setBoolean(this, "isfanhuied", false);//把是否点过一次返回初始化为没点过
    }


    /**
     * 用于监听返回键，防止点一次返回键就关闭了,初始化也放到resume方法里面一份
     * @param keyCode
     * @param event
     * @return
     */
    long waittime =2000;
    long touchtime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
            long currenttime=System.currentTimeMillis();
            System.out.println("从CurrentTimeMillis（）中获取的值"+currenttime);
            if ((currenttime-touchtime)>waittime){
                Toast.makeText(Setting.this,"再次点击返回退出",Toast.LENGTH_SHORT).show();
                touchtime=currenttime;
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
                //此处执行清除所有数据的操作！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！(要有finish和)
                mDatabaseHelper.todelete(true,null);
                mDatabaseHelper.close();
                Snackbar.make(view,"所有日程已被清除",Snackbar.LENGTH_SHORT).setAction("我知道啦", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
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

    /**
     * 这个是控件带的点击波纹效果
     * @param layoutRipple
     */
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
