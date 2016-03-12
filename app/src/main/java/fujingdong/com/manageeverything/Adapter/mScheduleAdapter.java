package fujingdong.com.manageeverything.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
        final mViewHolder holder = new mViewHolder(v);



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
                    holder.slider.setVisibility(View.VISIBLE);
                    holder.iv_edit.setImageResource(R.drawable.ic_unfold_less_24dp);
                }else{
                    holder.slider.setVisibility(View.GONE);
                    holder.iv_edit.setImageResource(R.drawable.ic_unfold_more_24dp);
                }
            }
        });

        holder.slider.setOnValueChangedListener(new Slider.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                if (value==holder.slider.getMax()) {
                    holder.cardBr.setVisibility(View.VISIBLE);
                    holder.cardBr.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //“完成”按钮点击事件

                        }
                    });
                }else{
                    holder.cardBr.setVisibility(View.GONE);
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


        public mViewHolder(View itemView) {
            super(itemView);
            cardTvTitle = (TextView) itemView.findViewById(R.id.card_tvtitle);
            cardTvContent = (TextView) itemView.findViewById(R.id.card_tvcontent);
            slider = (Slider) itemView.findViewById(R.id.sliderprogress);
            cardBr = (ButtonRectangle) itemView.findViewById(R.id.btn_complete);
            iv_edit= (ImageView) itemView.findViewById(R.id.iv_edit);
            iv_close= (ImageView) itemView.findViewById(R.id.iv_off);




        }
    }
}
