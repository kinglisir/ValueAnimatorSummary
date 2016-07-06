package com.example.valueanimatorsummary;


import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public abstract class BaseAnim {
	protected ValueAnimator animator = null;
	protected View targetView;
	
	public BaseAnim(final View target,int startValue,int endValue){
		this.targetView = target;
		
		animator = ValueAnimator.ofInt(startValue,endValue);
		animator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				//1.获取当前动画的值
				int animatedValue = (Integer) animator.getAnimatedValue();
				//2.根据animatedValue来执行具体的动画逻辑
				doAnim(animatedValue);
			}
		});
		//设置速度插值器
		setInterpolator();
	}
	/**
	 * 设置速度插值器
	 */
	public void setInterpolator() {
	}
	/**
	 * 每个子类根据int animatedValue来执行具体的动画逻辑。比如更改paddingTop，或者height
	 * @param animatedValue
	 */
	public abstract void doAnim(int animatedValue);
	
	/**
	 * 设置动画监听器
	 * @param listener
	 */
	public void setAnimationListener(AnimatorListener listener){
		if(listener!=null){
			animator.addListener(listener);
		}
	}
	
	/**
	 * 开启动画
	 * @param duration
	 */
	public void start(long duration){
		animator.setDuration(350);
		animator.start();
	}
}
