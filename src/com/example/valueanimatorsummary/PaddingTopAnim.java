package com.example.valueanimatorsummary;


import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * paddingTop�ı�Ķ���
 * @author Administrator
 *
 */
public class PaddingTopAnim extends BaseAnim{

	public PaddingTopAnim(View target, int startValue, int endValue) {
		super(target, startValue, endValue);
	}
	@Override
	public void doAnim(int animatedValue) {
		//2.��������ֵ����ΪpaddingTop
		targetView.setPadding(targetView.getPaddingLeft(),animatedValue, targetView.getPaddingRight()
				, targetView.getPaddingBottom());
	}
	
	@Override
	public void setInterpolator() {
//		animator.setInterpolator(new OvershootInterpolator());
	}
	
}
