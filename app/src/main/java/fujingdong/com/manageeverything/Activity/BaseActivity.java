package fujingdong.com.manageeverything.Activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fujingdong.com.manageeverything.R;

public class BaseActivity extends AppCompatActivity {

    @InjectView(R.id.toolbartitle)
    public TextView toolbartitle;
    @InjectView(R.id.toolbar)
    public Toolbar toolbar;
    @InjectView(R.id.appbarlayout)
    public AppBarLayout appbarlayout;
    @InjectView(R.id.rl_base)
    public RelativeLayout rlBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        rlBase.removeAllViews();
//        toolbar.setCollapsible(true);//折叠
        initData();
        initView();
    }


    public void initView() {

    }

    public void initData() {

    }

}
