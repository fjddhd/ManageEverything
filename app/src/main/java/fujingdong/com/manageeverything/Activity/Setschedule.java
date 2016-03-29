package fujingdong.com.manageeverything.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.gc.materialdesign.views.ButtonFlat;

import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;
import fujingdong.com.manageeverything.Utils.PrefUtils;

/**
 * Created by Administrator on 2016/3/10.
 * 日程设置页
 */
public class Setschedule extends BaseActivity {
    private MDatabaseHelper mDatabaseHelper=new MDatabaseHelper(this,"mDatabase",null,1);
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private AlertDialog clearAlertdialog;
    private AlertDialog showFastDialog;
    private View view;

    @Override
    public void initView() {
        super.initView();
        view = View.inflate(Setschedule.this, R.layout.set_schedule, rlBase);
        final TextInputLayout til1= (TextInputLayout) view.findViewById(R.id.til1);
        final TextInputLayout til2= (TextInputLayout) view.findViewById(R.id.til2);
        final TextInputLayout til3= (TextInputLayout) view.findViewById(R.id.til3);
        final TextInputLayout til4= (TextInputLayout) view.findViewById(R.id.til4);
        ButtonFlat bf= (ButtonFlat) view.findViewById(R.id.buttonflat);
        bf.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        til1.setHint("请载入日程标题");
        til2.setHint("请载入日程描述");
        til3.setHint("请载入当前日程进度");
        til4.setHint("请载入目标日程进度");
        editText1 = til1.getEditText();
        editText2 = til2.getEditText();
        editText3 = til3.getEditText();
        editText4 = til4.getEditText();
        if (PrefUtils.getBoolean(Setschedule.this,"isfastAddClose",false)){}else {
            showFastSelectDialog();
        }

        bf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1 = editText1.getText().toString().trim();
                text2 = editText2.getText().toString().trim();
                text3 = editText3.getText().toString().trim();
                text4 = editText4.getText().toString().trim();
                if (TextUtils.isEmpty(text1)){
                    Snackbar.make(view,"尚未载入日程标题",Snackbar.LENGTH_LONG).setAction("这就去载入", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                }else if (TextUtils.isEmpty(text2)){
                    Snackbar.make(view,"尚未载入日程内容",Snackbar.LENGTH_LONG).setAction("这就去载入", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                }else if (TextUtils.isEmpty(text3) && TextUtils.isEmpty(text4)){
                    showdialogForFast();//快速添加的对话框

                }else if (TextUtils.isEmpty(text3)){
                    Snackbar.make(view,"尚未载入当前日程进度",Snackbar.LENGTH_LONG).setAction("这就去载入", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                }else if (TextUtils.isEmpty(text4)){
                    Snackbar.make(view,"尚未载入目标日程进度",Snackbar.LENGTH_LONG).setAction("这就去载入", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                }else if (Integer.parseInt(text3)>=Integer.parseInt(text4)){
                    Snackbar.make(view,"目标进度要小于当前进度才可以哦",Snackbar.LENGTH_LONG).setAction("我明白啦", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).show();
                }else {
                    showdialog();
                }

            }
        });


//

    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("增加日程");

        toolbar.setNavigationIcon(R.drawable.arrow_left_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void addschedule(){
        mDatabaseHelper.insert(text1, text2, text3, text4, null);
        mDatabaseHelper.close();
    }
    private void addscheduleForFast(){
        mDatabaseHelper.insert(text1, text2, "0", "1", null);
        mDatabaseHelper.close();
    }
    private void showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Setschedule.this);
        clearAlertdialog = builder.create();
        builder.setTitle("确定添加");
        builder.setMessage("我是否要添加这个日程？");
        builder.setNegativeButton("先不添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAlertdialog.dismiss();
            }
        });
        builder.setPositiveButton("添加吧！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addschedule();//把所有内容添加到数据库的方法
                Snackbar.make(view,"添加已完成哦",Snackbar.LENGTH_LONG).setAction("我知道啦", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
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
    private void showdialogForFast(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Setschedule.this);
        clearAlertdialog = builder.create();
        builder.setTitle("确定快速添加");
        builder.setMessage("我是否要快速添加这个日程？");
        builder.setNegativeButton("先不添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearAlertdialog.dismiss();
            }
        });
        builder.setPositiveButton("添加吧！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addscheduleForFast();//把所有内容添加到数据库的方法
                Snackbar.make(view,"快速添加已完成哦",Snackbar.LENGTH_LONG).setAction("我知道啦", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
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
    private void showFastSelectDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Setschedule.this);
        showFastDialog = builder.create();
        builder.setTitle("快速添加的使用");
        builder.setMessage("当需要快速添加时，只需要填写完“日程标题”和“日程描述”就可以点击“我要添加日程”按钮。快速添加的默认当前进度为“0”，默认目标进度为“1”。");
        builder.setNegativeButton("我知道啦", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showFastDialog.dismiss();
            }
        });
        builder.setPositiveButton("不再提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addscheduleForFast();//把所有内容添加到数据库的方法
                PrefUtils.setBoolean(Setschedule.this,"isfastAddClose",true);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //什么也不做
                showFastDialog.dismiss();

            }
        });
        builder.show();

    }
}
