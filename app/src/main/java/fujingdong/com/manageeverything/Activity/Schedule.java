package fujingdong.com.manageeverything.Activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fujingdong.com.manageeverything.Adapter.mScheduleAdapter;
import fujingdong.com.manageeverything.Bean.ScheduleBean;
import fujingdong.com.manageeverything.Database.MDatabaseHelper;
import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/10.
 * 日程页
 */
public class Schedule extends BaseActivity {

    private MDatabaseHelper mDatabaseHelper;
    public List<ScheduleBean> list=new ArrayList<ScheduleBean>();
    @Override
    public void initView() {
        super.initView();
        View v = View.inflate(Schedule.this, R.layout.schedule, rlBase);
        RecyclerView rv= (RecyclerView) v.findViewById(R.id.rv_schedule);
        rv.setHasFixedSize(true);//使RecyclerView保持固定的大小,这样会提高RecyclerView的性能。
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new mScheduleAdapter(this,list));


    }

    @Override
    public void initData() {
        super.initData();
        toolbartitle.setText("我的日程");
        ScheduleBean s1=new ScheduleBean("title1","content1",50,100);
        ScheduleBean s2=new ScheduleBean("title2","content2",50,100);
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


    }


}
