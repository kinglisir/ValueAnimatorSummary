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
				//1.��ȡ��ǰ������ֵ
				int animatedValue = (Integer) animator.getAnimatedValue();
				//2.����animatedValue��ִ�о���Ķ����߼�
				doAnim(animatedValue);
			}
		});
		//�����ٶȲ�ֵ��
		setInterpolator();
	}
	/**
	 * �����ٶȲ�ֵ��
	 */
	public void setInterpolator() {
	}
	/**
	 * ÿ���������int animatedValue��ִ�о���Ķ����߼����������paddingTop������height
	 * @param animatedValue
	 */
	public abstract void doAnim(int animatedValue);
	
	/**
	 * ���ö���������
	 * @param listener
	 */
	public void setAnimationListener(AnimatorListener listener){
		if(listener!=null){
			animator.addListener(listener);
		}
	}
	
	/**
	 * ��������
	 * @param duration
	 */
	public void start(long duration){
		animator.setDuration(350);
		animator.start();
	}
}
