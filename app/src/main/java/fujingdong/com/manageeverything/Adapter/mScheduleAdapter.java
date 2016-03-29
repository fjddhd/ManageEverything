package fujingdong.com.manageeverything.Adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.Slider;
import com.gc.materialdesign.widgets.SnackBar;

import java.util.List;
import java.util.zip.Inflater;

import fujingdong.com.manageeverything.Activity.BaseActivity;
import fujingdong.com.manageeverything.Activity.Schedule;
import fujingdong.com.manageeverything.Bean.ScheduleBean;
import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/12.
 */
public class mScheduleAdapter extends RecyclerView.Adapter<mScheduleAdapter.mViewHolder> {

    public Schedule context;
    public List<ScheduleBean> list;
    private MDatabaseHelper mDatabaseHelper;
<<<<<<< HEAD
=======
    private AlertDialog clearAlertdialog;
    private AlertDialog changetitleAlertdialog;
    private AlertDialog changecontentAlertdialog;
>>>>>>> 38a2744dbd2ca36250fdb2f3e282912c6c5c1a8e


    public mScheduleAdapter(Schedule ctx, List<ScheduleBean> list, MDatabaseHelper mDatabaseHelper) {
        this.context = ctx;
        this.list=list;
        this.mDatabaseHelper=mDatabaseHelper;
    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v=View.inflate(context, R.layout.rv_schedule_item, null);
        View v = LayoutInflater.from(context).inflate(R.layout.rv_schedule_item, parent, false);//为了让其中的cardview获得属性（这里主要是margin）
        final mViewHolder holder = new mViewHolder(v);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getPosition();
//                Toast.makeText(v.getContext(), "Item click. Position:" +
//                        position, Toast.LENGTH_SHORT).show();
//            }
//        });



        return holder;
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, final int position) {
        holder.cardTvTitle.setText(list.get(position).title);
        holder.cardTvContent.setText(list.get(position).content);
        holder.slider.setMax(list.get(position).progressMax);
        holder.slider.setValue(list.get(position).progress);
        holder.idRecord.setText(list.get(position).getId() + "");//这个隐藏的textview用于存储主键
        final String id= (String) holder.idRecord.getText();
        //判断是否点过完成
        if ("ok".equals(list.get(position).getBeizhu())){//判断不能反过来，因为如果得到的备注是null，null不具备equal方法
            holder.iv_finish.setVisibility(View.VISIBLE);
            holder.iv_edit.setVisibility(View.GONE);
            holder.cardBr.setVisibility(View.GONE);
            holder.sliderParent.setVisibility(View.GONE);
            holder.iv_edit.setClickable(false);//关闭已经隐藏的edit键的点击能力
//            holder.cardTvTitle.setLongClickable(false);//关闭标题的长按修改事件,由于必须设置长点监听才能有效，所以再这不用
        }else {//只有没完成的时候才给设置长按监听显示dialog
            holder.cardTvTitle.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showchangeTitleDialog(id, position);
                    return false;
                }
            });
            holder.cardTvContent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showchangeContentDialog(id,position);
                    return false;
                }
            });
        }


        holder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //“关闭”按钮的点击事件(最好设置一个dialog，已经设置)
                final RotateAnimation rotate = new RotateAnimation(0, -45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);//旋转时间
                rotate.setFillAfter(false);//保持动画状态，这里由于会在某些时候出现bug，所以不保持以避免这个bug
                holder.iv_close.startAnimation(rotate);
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        clearAlertdialog = builder.create();
                        builder.setTitle("清除日程");
                        builder.setMessage("该操作无法恢复，请再次确认是否需要清除这个日程");
                        builder.setNegativeButton("先不清除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clearAlertdialog.dismiss();
                            }
                        });
                        builder.setPositiveButton("清除吧！", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    mDatabaseHelper.todelete(false, id);
                                    mDatabaseHelper.close();
                                    context.initDatabaseData();
//                    schedule.initDatabaseData();//一定会崩，用来测试snackbar
//                            notifyDataSetChanged();//用这个容易出现异常
                                    notifyItemRemoved(position);
                                    if (list.isEmpty()) {//当列表空了的时候需要重新加载 schedule_nothing 这个空提醒布局
                                        context.initView();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Snackbar.make(holder.cardBr, "删除未成功", Snackbar.LENGTH_SHORT).setAction("我知道啦", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    }).show();
                                }

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
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //“编辑“按钮的点击事件，用于slider的显示和隐藏
                if (holder.sliderParent.getVisibility() == View.GONE) {
                    //如果进度条处于隐藏状态，按下按钮，则会把进度条展示出来，并且更改图标，然后再判断完成按钮需不需要展示
                    holder.sliderParent.setVisibility(View.VISIBLE);
                    holder.iv_edit.setImageResource(R.drawable.ic_unfold_less_24dp);
                    AlphaAnimation alpha = new AlphaAnimation(0, 1);
                    alpha.setDuration(1500);//时间
                    alpha.setFillAfter(true);//保持动画状态
                    holder.sliderParent.startAnimation(alpha);
//                    System.out.println("按钮准备开始显示啊！！");
                    if (holder.slider.getValue() == holder.slider.getMax()) {//如果此时进度条数值等于最大值，那就显示完成按钮
//                        System.out.println("按钮显示啊！！");
                        holder.cardBr.setVisibility(View.VISIBLE);//此时保留了关闭时的缩放到最小的动画，需要动画展示出来
                        ScaleAnimation scale3 = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        scale3.setDuration(1500);//时间
                        scale3.setFillAfter(true);//保持动画状态
                        holder.cardBr.startAnimation(scale3);

                    }

                } else {//如果进度条处于非隐藏状态，点击按钮，会把进度条隐藏并更改按钮图片，如果这个时候完成按钮也是显示的，则也要隐藏它
                    if (holder.cardBr.getVisibility() == View.VISIBLE) {//如果此时完成按钮处于非隐藏状态，那也需要将完成按钮一并隐藏掉
                        ScaleAnimation scale2 = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        scale2.setDuration(1500);//时间
                        scale2.setFillAfter(true);//保持动画状态
                        holder.cardBr.startAnimation(scale2);
                        scale2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                holder.cardBr.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                    holder.iv_edit.setImageResource(R.drawable.ic_unfold_more_24dp);//先换图标体验更好
                    AlphaAnimation alpha1 = new AlphaAnimation(1, 0);
                    alpha1.setDuration(1500);//时间
                    alpha1.setFillAfter(true);//保持动画状态
                    alpha1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holder.sliderParent.setVisibility(View.GONE);


                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    holder.slider.startAnimation(alpha1);
                }
            }
        });

        holder.slider.setOnValueChangedListener(new Slider.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                //数据库更新操作，切记不要改别的数值，不然会报错
                SQLiteDatabase writableDatabase = mDatabaseHelper.getWritableDatabase();
                ContentValues cv = new ContentValues();
                String[] args = {id};
                cv.put("value", value);
                writableDatabase.update("mDatabase", cv, "scheduleId=?",
                        args);
                writableDatabase.close();
                mDatabaseHelper.close();

                if (value == holder.slider.getMax()) {//数值等于最大值

                    holder.cardBr.setVisibility(View.VISIBLE);
                    ScaleAnimation scale0 = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale0.setDuration(1500);//时间
                    scale0.setFillAfter(true);//保持动画状态
                    holder.cardBr.startAnimation(scale0);
                    holder.cardBr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //“完成”按钮点击事件
                            //数据库更新操作，在备注存储点击过完成，切记不要改别的数值，不然会报错
                            SQLiteDatabase writableDatabase = mDatabaseHelper.getWritableDatabase();
                            ContentValues cv = new ContentValues();
                            String[] args = {id};
                            cv.put("beizhu", "ok");
                            writableDatabase.update("mDatabase", cv, "scheduleId=?",
                                    args);
                            writableDatabase.close();
                            mDatabaseHelper.close();

                            holder.iv_finish.setVisibility(View.VISIBLE);
                            holder.iv_edit.setVisibility(View.GONE);
                            AlphaAnimation alpha = new AlphaAnimation(0, 1);
                            alpha.setDuration(1500);//时间
                            alpha.setFillAfter(true);//保持动画状态
                            AlphaAnimation alpha1 = new AlphaAnimation(1, 0);
                            alpha1.setDuration(1500);//时间
                            alpha1.setFillAfter(true);//保持动画状态
                            TranslateAnimation ta1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f
                                    , Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);
                            ta1.setDuration(1500);//时间
                            ta1.setFillAfter(true);//保持动画状态
                            TranslateAnimation ta2 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -0.5f
                                    , Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -1);
                            ta2.setDuration(1500);//时间
                            ta2.setFillAfter(true);//保持动画状态
                            ScaleAnimation scale = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                            scale.setDuration(1500);//时间
                            scale.setFillAfter(true);//保持动画状态
                            ScaleAnimation scale1 = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                            scale1.setDuration(1500);//时间
                            scale1.setFillAfter(true);//保持动画状态
                            AnimationSet set1 = new AnimationSet(false);
                            AnimationSet set2 = new AnimationSet(false);
                            set1.addAnimation(scale);
                            set1.addAnimation(ta1);
                            set2.addAnimation(scale1);
                            set2.addAnimation(ta2);
                            holder.sliderParent.startAnimation(set1);
                            holder.cardBr.startAnimation(set2);
                            set1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    holder.cardBr.setVisibility(View.GONE);
                                    holder.sliderParent.setVisibility(View.GONE);
                                    holder.iv_edit.setClickable(false);//关闭已经隐藏的edit键的点击能力
                                    holder.cardTvTitle.setLongClickable(false);//关闭长按
                                    holder.cardTvContent.setLongClickable(false);//关闭长按
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            holder.iv_edit.startAnimation(alpha1);
                            holder.iv_finish.startAnimation(alpha);
                        }
                    });
                } else {//数值不等于最大值
                    ScaleAnimation scale4 = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale4.setDuration(1500);//时间
                    scale4.setFillAfter(true);//保持动画状态
                    holder.cardBr.startAnimation(scale4);
                    scale4.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holder.cardBr.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
        //这两个tv设置一个触摸动画用于提醒
        holder.cardTvTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                holder.cardTvTitle.startAnimation(scaleForTixing());
                return false;
            }
        });
        holder.cardTvContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                holder.cardTvContent.startAnimation(scaleForTixing());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private ScaleAnimation scaleForTixing(){
        ScaleAnimation scale = new ScaleAnimation(1, 0.95f, 1,0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(500);//时间
        scale.setFillAfter(false);//保持动画状态
        return scale;
    }
    private void showchangeTitleDialog(final String id, final int position){
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        changetitleAlertdialog = builder.create();
        final View view= View.inflate(context,R.layout.change_title_dialog,null);
//        System.out.println("出来吧对话框");
        changetitleAlertdialog.setView(view);
        final TextInputLayout tildDalogChangeTitle= (TextInputLayout) view.findViewById(R.id.til_dialog_changetitle);
        ButtonFlat bfquxiao= (ButtonFlat) view.findViewById(R.id.bf_dialog_change_title_quxiao);
        ButtonFlat bfqueren= (ButtonFlat) view.findViewById(R.id.bf_dialog_change_title_queren);
        tildDalogChangeTitle.setHint("请输入新的标题");
        bfqueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tildDalogChangeTitle.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(s)) {//框内是空的
                    tildDalogChangeTitle.setError("标题不可以为空哦");
                } else {
                    tildDalogChangeTitle.setErrorEnabled(false);
                    SQLiteDatabase writableDatabase = mDatabaseHelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    String[] args = {id};
                    cv.put("title", s);
                    writableDatabase.update("mDatabase", cv, "scheduleId=?",
                            args);
                    writableDatabase.close();
                    changetitleAlertdialog.dismiss();
                    context.initDatabaseData();
                    notifyItemChanged(position);
                }
            }
        });
        bfquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changetitleAlertdialog.dismiss();
            }
        });
        changetitleAlertdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //什么也不做
                changetitleAlertdialog.dismiss();

            }
        });
        changetitleAlertdialog.show();
    }
    private void showchangeContentDialog(final String id, final int position){
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        changecontentAlertdialog = builder.create();
        final View view= View.inflate(context,R.layout.change_content_dialog,null);
//        System.out.println("出来吧对话框");
        changecontentAlertdialog.setView(view);
        final TextInputLayout tildDalogChangeTitle= (TextInputLayout) view.findViewById(R.id.til_dialog_changetitle);
        ButtonFlat bfquxiao= (ButtonFlat) view.findViewById(R.id.bf_dialog_change_title_quxiao);
        ButtonFlat bfqueren= (ButtonFlat) view.findViewById(R.id.bf_dialog_change_title_queren);
        tildDalogChangeTitle.setHint("请输入新的描述");
        bfqueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tildDalogChangeTitle.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(s)) {//框内是空的
                    tildDalogChangeTitle.setError("描述不可以为空哦");
                } else {
                    tildDalogChangeTitle.setErrorEnabled(false);
                    SQLiteDatabase writableDatabase = mDatabaseHelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    String[] args = {id};
                    cv.put("content", s);
                    writableDatabase.update("mDatabase", cv, "scheduleId=?",
                            args);
                    writableDatabase.close();
                    changecontentAlertdialog.dismiss();
                    context.initDatabaseData();
                    notifyItemChanged(position);
                }
            }
        });
        bfquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changecontentAlertdialog.dismiss();
            }
        });
        changecontentAlertdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //什么也不做
                changecontentAlertdialog.dismiss();

            }
        });
        changecontentAlertdialog.show();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {

        public TextView cardTvTitle;
        public TextView cardTvContent;
        public Slider slider;
        public ButtonRectangle cardBr;
        public ImageView iv_edit;
        public ImageView iv_close;
        public ImageView iv_finish;
        public TextView idRecord;
        public LinearLayout sliderParent;


        public mViewHolder(View itemView) {
            super(itemView);
            cardTvTitle = (TextView) itemView.findViewById(R.id.card_tvtitle);
            cardTvContent = (TextView) itemView.findViewById(R.id.card_tvcontent);
            sliderParent = (LinearLayout) itemView.findViewById(R.id.sliderprogress);
            slider= (Slider) sliderParent.getChildAt(1);
            cardBr = (ButtonRectangle) itemView.findViewById(R.id.btn_complete);
            iv_edit= (ImageView) itemView.findViewById(R.id.iv_edit);
            iv_close= (ImageView) itemView.findViewById(R.id.iv_off);
            iv_finish= (ImageView) itemView.findViewById(R.id.iv_finish);
            idRecord= (TextView) itemView.findViewById(R.id.item_idrecord);




        }
    }
}
