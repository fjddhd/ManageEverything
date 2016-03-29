package fujingdong.com.manageeverything.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fujingdong.com.manageeverything.Adapter.mScheduleAdapter;
import fujingdong.com.manageeverything.Bean.ScheduleBean;
import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;
import fujingdong.com.manageeverything.Utils.RecycleViewDivider;

/**
 * Created by Administrator on 2016/3/10.
 * 日程页
 */
public class Schedule extends BaseActivity {

    private MDatabaseHelper mDatabaseHelper=new MDatabaseHelper(this,"mDatabase",null,1);
    public List<ScheduleBean> list=new ArrayList<ScheduleBean>();
    private View v;

    @Override
    public void initView() {
        super.initView();
        if (list.isEmpty()){
            v = View.inflate(Schedule.this, R.layout.schedule_nothing, rlBase);
            TextView textno= (TextView) v.findViewById(R.id.textnothing);
            textno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {
            rlBase.setBackgroundColor(getResources().getColor(R.color.light_gery));
            View v = View.inflate(Schedule.this, R.layout.schedule, rlBase);
            RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_schedule);
            rv.setHasFixedSize(true);//使RecyclerView保持固定的大小,这样会提高RecyclerView的性能。
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new mScheduleAdapter(this, list,mDatabaseHelper));
            //获取设备密度
            DisplayMetrics dm=new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            int density = (int) dm.density;
            rv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL,1*density,R.color.light_gery));//添加分割线，方法在utils中，其中高度是像素
        }


    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("我的日程");
        toolbar.setNavigationIcon(R.drawable.arrow_left_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




//        list.add(s1);
//        list.add(s2);
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打是大幅度的范德萨范德萨发第三方的范德萨发算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师发送到发送到搞活动的恢复黄金分割进货价格的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实第三方第三方士大夫爽肤水的方法第三方第三方反倒是大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));
//        list.add(new ScheduleBean("标题","撒的撒打算的撒的撒打算的实打实大师的撒",50,200));

//        ScheduleBean s1=new ScheduleBean();
//        ScheduleBean s2=new ScheduleBean();
//        s1.setId(0);
//        s1.setTitle("sada");
//        s1.setContent("asdsadsa");
//        s1.setProgress(12);
//        s1.setProgressMax(44);
//        s2.setId(0);
//        s2.setTitle("sada");
//        s2.setContent("asdsadsa");
//        s2.setProgress(12);
//        s2.setProgressMax(44);
//        list.add(s1);
//        list.add(s2);

        initDatabaseData();



    }
    /**
     * 将数据库中所有数据加载进来
     */
    public void initDatabaseData(){
        list.clear();
        Cursor all = mDatabaseHelper.getAll(null, "scheduleId");
        for(all.moveToFirst();!all.isAfterLast();all.moveToNext()){
            if (all!=null) {
                try {
                    ScheduleBean scheduleBean = new ScheduleBean();
                    scheduleBean.setId(Integer.parseInt(all.getString(0)));
                    scheduleBean.setTitle(all.getString(1));
                    scheduleBean.setContent(all.getString(2));
                    scheduleBean.setProgress(Integer.parseInt(all.getString(3)));
                    scheduleBean.setProgressMax(Integer.parseInt(all.getString(4)));


                    scheduleBean.setBeizhu(all.getString(5));
//                System.out.println(scheduleBean.toString());
                    list.add(scheduleBean);
                } catch (NumberFormatException e) {
                    Snackbar.make(v,"数据库数据加载出错",Snackbar.LENGTH_SHORT).setAction("我知道啦", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                }
                //注意！！！（数据库中第一个id是1，list中第一个是0,不过适配器中用了一个不可见不占位的textview去记录从数据库得到的scheduleId）
            }
        }
        all.close();
        mDatabaseHelper.close();
    }


}
