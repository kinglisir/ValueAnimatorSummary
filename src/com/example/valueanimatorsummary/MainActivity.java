package com.example.valueanimatorsummary;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends ActionBarActivity implements OnClickListener,OnGlobalLayoutListener{

	private LinearLayout ll_value;//被点击的对象
	private int mineValue;//最小值
	private int maxValue;//最大值
	private LinearLayout ll_root;//执行动画的 view
	private int minPaddingTop;//缩进去后的paddingTop
	private int maxPaddingTop;//最大的paddingTop
	private boolean isExtend = false;//是否是伸展 ，默认是收缩的
	private boolean isAniming = false;//是否正在执行动画
	private ImageView iv_other_arrow;//箭头
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll_value = (LinearLayout) findViewById(R.id.ll_value);
		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		iv_other_arrow = (ImageView) findViewById(R.id.iv_other_arrow);
		
		
		ll_value.setOnClickListener(this);
		//该方法可以确保获取  控件的 宽高
		ll_root.getViewTreeObserver().addOnGlobalLayoutListener(this);
		
		
		
	
	}
	
		


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==ll_value){
			if(isAniming){
				//如果正在执行动画，则不应该再执行以下代码
				return;
			}
			
			PaddingTopAnim anim = null;
			if(isExtend){//值动画
				//执行收缩动画
				anim = new PaddingTopAnim(ll_root, maxPaddingTop, minPaddingTop);
			}else {
				//执行伸展动画
				anim = new PaddingTopAnim(ll_root, minPaddingTop, maxPaddingTop);
			}
			anim.start(350);
			
			//将标记置为反值
			isExtend = !isExtend;
			//将箭头旋转
			ViewPropertyAnimator.animate(iv_other_arrow)
			.rotationBy(180)
			.setListener(new MyListener())
			.setDuration(350)
			.start();
	}
	
	
	}

	
//该方法中可以确保获取控件的值
	public void onGlobalLayout() {
		// TODO Auto-generated method stub
		//先清除监听
		ll_root.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		//获取到高度
		maxPaddingTop = ll_root.getPaddingTop();
		minPaddingTop = -1*ll_root.getMeasuredHeight()+maxPaddingTop;
		ll_root.setPadding(ll_root.getPaddingLeft(),minPaddingTop,ll_root.getPaddingRight()
				, ll_root.getPaddingBottom());
		//布局从左边移入
		ViewHelper.setTranslationX(ll_value,-1*ll_value.getMeasuredWidth());
		ViewPropertyAnimator.animate(ll_value)
		.translationXBy(ll_value.getMeasuredWidth())
		.setInterpolator(new OvershootInterpolator())
		.setDuration(1000)
		.setStartDelay(400)
		.start();
	}
	
	class MyListener implements AnimatorListener{
		@Override
		public void onAnimationCancel(Animator arg0) {
		}
		@Override
		public void onAnimationEnd(Animator arg0) {
			isAniming = false;
		}
		@Override
		public void onAnimationRepeat(Animator arg0) {
		}
		@Override
		public void onAnimationStart(Animator arg0) {
			isAniming = true;
		}
	}
}
