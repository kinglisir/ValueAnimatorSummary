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

	private LinearLayout ll_value;//������Ķ���
	private int mineValue;//��Сֵ
	private int maxValue;//���ֵ
	private LinearLayout ll_root;//ִ�ж����� view
	private int minPaddingTop;//����ȥ���paddingTop
	private int maxPaddingTop;//����paddingTop
	private boolean isExtend = false;//�Ƿ�����չ ��Ĭ����������
	private boolean isAniming = false;//�Ƿ�����ִ�ж���
	private ImageView iv_other_arrow;//��ͷ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll_value = (LinearLayout) findViewById(R.id.ll_value);
		ll_root = (LinearLayout) findViewById(R.id.ll_root);
		iv_other_arrow = (ImageView) findViewById(R.id.iv_other_arrow);
		
		
		ll_value.setOnClickListener(this);
		//�÷�������ȷ����ȡ  �ؼ��� ���
		ll_root.getViewTreeObserver().addOnGlobalLayoutListener(this);
		
		
		
	
	}
	
		


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==ll_value){
			if(isAniming){
				//�������ִ�ж�������Ӧ����ִ�����´���
				return;
			}
			
			PaddingTopAnim anim = null;
			if(isExtend){//ֵ����
				//ִ����������
				anim = new PaddingTopAnim(ll_root, maxPaddingTop, minPaddingTop);
			}else {
				//ִ����չ����
				anim = new PaddingTopAnim(ll_root, minPaddingTop, maxPaddingTop);
			}
			anim.start(350);
			
			//�������Ϊ��ֵ
			isExtend = !isExtend;
			//����ͷ��ת
			ViewPropertyAnimator.animate(iv_other_arrow)
			.rotationBy(180)
			.setListener(new MyListener())
			.setDuration(350)
			.start();
	}
	
	
	}

	
//�÷����п���ȷ����ȡ�ؼ���ֵ
	public void onGlobalLayout() {
		// TODO Auto-generated method stub
		//���������
		ll_root.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		//��ȡ���߶�
		maxPaddingTop = ll_root.getPaddingTop();
		minPaddingTop = -1*ll_root.getMeasuredHeight()+maxPaddingTop;
		ll_root.setPadding(ll_root.getPaddingLeft(),minPaddingTop,ll_root.getPaddingRight()
				, ll_root.getPaddingBottom());
		//���ִ��������
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
