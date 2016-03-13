package fujingdong.com.manageeverything.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.gc.materialdesign.views.Slider;

import java.util.List;

import fujingdong.com.manageeverything.Activity.Schedule;
import fujingdong.com.manageeverything.Bean.ScheduleBean;
import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/12.
 */
public class mScheduleAdapter extends RecyclerView.Adapter<mScheduleAdapter.mViewHolder> {

    public Context context;
    public List<ScheduleBean> list;



    public mScheduleAdapter(Context ctx, List<ScheduleBean> list) {
        this.context = ctx;
        this.list=list;
    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=View.inflate(context, R.layout.rv_schedule_item, null);
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_schedule_item, parent, false);
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

        holder.iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //“关闭”按钮的点击事件
            }
        });
        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //“编辑“按钮的点击事件，用于slider的显示和隐藏
                if (holder.slider.getVisibility()==View.GONE){
                    //如果进度条处于隐藏状态，按下按钮，则会把进度条展示出来，并且更改图标，然后再判断完成按钮需不需要展示
                    holder.slider.setVisibility(View.VISIBLE);
                    holder.iv_edit.setImageResource(R.drawable.ic_unfold_less_24dp);
                    AlphaAnimation alpha=new AlphaAnimation(0,1);
                    alpha.setDuration(1500);//时间
                    alpha.setFillAfter(true);//保持动画状态
                    holder.slider.startAnimation(alpha);
                    if (holder.slider.getValue()==holder.slider.getMax()){//如果此时进度条数值等于最大值，那就显示完成按钮
                        holder.cardBr.setVisibility(View.VISIBLE);//此时保留了关闭时的缩放到最小的动画，需要动画展示出来
                        ScaleAnimation scale3 = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        scale3.setDuration(1500);//时间
                        scale3.setFillAfter(true);//保持动画状态
                        holder.cardBr.startAnimation(scale3);

                    }

                }else{//如果进度条处于非隐藏状态，点击按钮，会把进度条隐藏并更改按钮图片，如果这个时候完成按钮也是显示的，则也要隐藏它
                    if (holder.cardBr.getVisibility()==View.VISIBLE){//如果此时完成按钮处于非隐藏状态，那也需要将完成按钮一并隐藏掉
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
                    AlphaAnimation alpha1=new AlphaAnimation(1,0);
                    alpha1.setDuration(1500);//时间
                    alpha1.setFillAfter(true);//保持动画状态
                    holder.slider.startAnimation(alpha1);
                    alpha1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            holder.slider.setVisibility(View.GONE);


                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        });

        holder.slider.setOnValueChangedListener(new Slider.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                if (value==holder.slider.getMax()) {
                    holder.cardBr.setVisibility(View.VISIBLE);
                    ScaleAnimation scale0 = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale0.setDuration(1500);//时间
                    scale0.setFillAfter(true);//保持动画状态
                    holder.cardBr.startAnimation(scale0);
                    holder.cardBr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //“完成”按钮点击事件
                            holder.iv_finish.setVisibility(View.VISIBLE);
                            holder.iv_edit.setVisibility(View.GONE);
                            AlphaAnimation alpha=new AlphaAnimation(0,1);
                            alpha.setDuration(1500);//时间
                            alpha.setFillAfter(true);//保持动画状态
                            AlphaAnimation alpha1=new AlphaAnimation(1,0);
                            alpha1.setDuration(1500);//时间
                            alpha1.setFillAfter(true);//保持动画状态
                            TranslateAnimation ta1=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0.5f
                            ,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,1);
                            ta1.setDuration(1500);//时间
                            ta1.setFillAfter(true);//保持动画状态
                            TranslateAnimation ta2=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-0.5f
                                    ,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1);
                            ta2.setDuration(1500);//时间
                            ta2.setFillAfter(true);//保持动画状态
                            ScaleAnimation scale = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                            scale.setDuration(1500);//时间
                            scale.setFillAfter(true);//保持动画状态
                            ScaleAnimation scale1 = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                            scale1.setDuration(1500);//时间
                            scale1.setFillAfter(true);//保持动画状态
                            AnimationSet set1=new AnimationSet(false);
                            AnimationSet set2=new AnimationSet(false);
                            set1.addAnimation(scale);
                            set1.addAnimation(ta1);
                            set2.addAnimation(scale1);
                            set2.addAnimation(ta2);
                            holder.slider.startAnimation(set1);
                            holder.cardBr.startAnimation(set2);
                            set1.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    holder.cardBr.setVisibility(View.GONE);
                                    holder.slider.setVisibility(View.GONE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            holder.iv_edit.startAnimation(alpha1);
                            holder.iv_finish.startAnimation(alpha);
                        }
                    });
                }else{//数值不等于最大值
                    ScaleAnimation scale4 = new ScaleAnimation(1, 0, 1,0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {

        public TextView cardTvTitle;
        public TextView cardTvContent;
        public Slider slider;
        public ButtonRectangle cardBr;
        public ImageView iv_edit;
        public ImageView iv_close;
        public ImageView iv_finish;


        public mViewHolder(View itemView) {
            super(itemView);
            cardTvTitle = (TextView) itemView.findViewById(R.id.card_tvtitle);
            cardTvContent = (TextView) itemView.findViewById(R.id.card_tvcontent);
            slider = (Slider) itemView.findViewById(R.id.sliderprogress);
            cardBr = (ButtonRectangle) itemView.findViewById(R.id.btn_complete);
            iv_edit= (ImageView) itemView.findViewById(R.id.iv_edit);
            iv_close= (ImageView) itemView.findViewById(R.id.iv_off);
            iv_finish= (ImageView) itemView.findViewById(R.id.iv_finish);




        }
    }
}
