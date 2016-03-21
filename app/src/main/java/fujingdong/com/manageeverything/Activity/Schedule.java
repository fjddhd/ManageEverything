package fujingdong.com.manageeverything.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;

import java.util.ArrayList;
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
    @Override
    public void initView() {
        super.initView();
        if (list.isEmpty()){
            View v = View.inflate(Schedule.this, R.layout.schedule_nothing, rlBase);
            TextView textno= (TextView) v.findViewById(R.id.textnothing);
            textno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {
            View v = View.inflate(Schedule.this, R.layout.schedule, rlBase);
            RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv_schedule);
            rv.setHasFixedSize(true);//使RecyclerView保持固定的大小,这样会提高RecyclerView的性能。
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new mScheduleAdapter(this, list,mDatabaseHelper));
            rv.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.VERTICAL,10,R.color.light_gery));//添加分割线，方法在utils中，其中高度是像素
        }


    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("我的日程");
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
                ScheduleBean scheduleBean = new ScheduleBean();
                scheduleBean.setId(Integer.parseInt(all.getString(0)));
                scheduleBean.setTitle(all.getString(1));
                scheduleBean.setContent(all.getString(2));
                scheduleBean.setProgress(Integer.parseInt(all.getString(3)));
                scheduleBean.setProgressMax(Integer.parseInt(all.getString(4)));
                
                scheduleBean.setBeizhu(all.getString(5));
//                System.out.println(scheduleBean.toString());
                list.add(scheduleBean);
                //注意！！！（数据库中第一个id是1，list中第一个是0）
            }
        }
        all.close();
        mDatabaseHelper.close();
    }


}
