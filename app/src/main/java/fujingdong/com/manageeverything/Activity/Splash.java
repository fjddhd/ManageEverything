package fujingdong.com.manageeverything.Activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fujingdong.com.manageeverything.R;

/**
 * Created by Administrator on 2016/3/10.
 */
public class Splash extends Activity {
    @InjectView(R.id.iv_manage)
    ImageView ivManage;
    @InjectView(R.id.everything)
    ImageView everything;
    @InjectView(R.id.rl_splash)
    RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        ButterKnife.inject(this);
        initAnimation();

    }

    public void initAnimation() {
//        TranslateAnimation ta=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0
//        ,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0);
//        ta.setDuration(1000);//时间
//        ta.setFillAfter(true);//保持动画状态
//        everything.startAnimation(ta);
//        //渐变动画
//        AlphaAnimation alpha=new AlphaAnimation(0,1);
//        alpha.setDuration(1500);//时间
//        alpha.setFillAfter(true);//保持动画状态
//        ivManage.startAnimation(alpha);
        //缩放动画
        ScaleAnimation scale = new ScaleAnimation(0.7f, 0.9f, 0.7f, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(2000);//时间
        scale.setFillAfter(true);//保持动画状态
        ScaleAnimation scale1 = new ScaleAnimation(0.58f, 0.8f, 0.58f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale1.setDuration(2000);//时间
        scale1.setFillAfter(true);//保持动画状态
        everything.startAnimation(scale);
        ivManage.startAnimation(scale1);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                initSecondAni();
                EnterNext();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    /**
     * 页面跳转动画
     */
//    public void initSecondAni(){
//        Animator animator = ViewAnimationUtils.createCircularReveal(
//                rlSplash,
//                rlSplash.getWidth() / 2,
//                rlSplash.getHeight() / 2,
//                rlSplash.getWidth(),
//                0);
//        animator.setInterpolator(new AccelerateDecelerateInterpolator());
//        animator.setDuration(2000);
//        animator.start();
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                EnterNext();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });

//    }
    public void EnterNext(){
        startActivity(new Intent(Splash.this,Setting.class));
        finish();
    }
}
