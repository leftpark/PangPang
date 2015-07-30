package com.leftpark.android.pangpang;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener, OnLongClickListener{
	
	private static final String TAG = "MainActivity";
	
	private final int HANDLE_EVENT_DRAW_AGAIN = 1;
	
	private final int PLAYER_MOVEMENT_PIXEL = 20;
	
	ArrayList<Circle> mCircles;
	ArrayList<Rectangle> mRectangles;
	Rectangle mPlayer1;
	FigureView mFigureView;
	
	LinearLayout mLinearLayout;
	
	Button mBtn_Left;
	Button mBtn_Right;
	
	boolean mClicked = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d(TAG,"onCraet(S)");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
		Log.d(TAG,"l.width="+mLinearLayout.getWidth()+", l.height="+mLinearLayout.getHeight());
		
		mFigureView = new FigureView(this);
		
		mCircles = new ArrayList<Circle>();
		
		Circle c1 = new Circle(300, 100, 20, Color.RED);
		mCircles.add(c1);
		
		Circle c2 = new Circle(100, 200, 50, Color.BLUE);
		mCircles.add(c2);
		
		Circle c3 = new Circle(500, 150, 30, Color.BLACK);
		mCircles.add(c3);
		
		Circle c4 = new Circle(600, 200, 40, Color.YELLOW);
		mCircles.add(c4);
		
		Circle c5 = new Circle(700, 200, 10, Color.GREEN);
		mCircles.add(c5);
		
		mFigureView.setCircles(mCircles);
		
		// Rectangle
		/*
		mRectangles = new ArrayList<Rectangle>();
		Rectangle r1 = new Rectangle(400, 220, 420, 253);
		mRectangles.add(r1);
		mFigureView.setRectangles(mRectangles);
		*/
		
		// Player
		mPlayer1 = new Rectangle(400, 220, 420, 253);
		mFigureView.setPlayer1(mPlayer1);

		mLinearLayout.addView(mFigureView);
		
		NumberThread nt = new NumberThread(true);
		nt.start();
	}
	
	private void initView() {
		mLinearLayout = (LinearLayout)findViewById(R.id.linearLayout1);
		
		mBtn_Left = (Button)findViewById(R.id.btn_left);
		mBtn_Left.setOnClickListener(this);
		mBtn_Left.setOnLongClickListener(this);
		
		mBtn_Right = (Button)findViewById(R.id.btn_right);
		mBtn_Right.setOnClickListener(this);
		mBtn_Right.setOnLongClickListener(this);
	}
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			//Log.d(TAG,"msg.what="+msg.what);
			switch(msg.what) {
			case HANDLE_EVENT_DRAW_AGAIN:
				mFigureView.callCircleDraw(mCircles);
				break;
			}
		}
	};
	
	class NumberThread extends Thread{
		private int i = 0;
		private boolean isPlay = false;
		
		public NumberThread(boolean isPlay) {
			this.isPlay = isPlay;
		}
		
		@Override
		public void run() {
			super.run();
			while(isPlay) {
				try {
					mClicked = false;
					Thread.sleep(10);
					Message msg = new Message();
					msg.what = HANDLE_EVENT_DRAW_AGAIN;
					mHandler.sendMessage(msg);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int left;
		int right;
		switch(v.getId()) {
		case R.id.btn_left:
			//if (mClicked) return;
			left = mPlayer1.getLeft();
			right = mPlayer1.getRight();
			
			if (PLAYER_MOVEMENT_PIXEL <= left) {
				mPlayer1.setLeft(left-PLAYER_MOVEMENT_PIXEL);
				mPlayer1.setRight(right-PLAYER_MOVEMENT_PIXEL);
			} else {
				mPlayer1.setLeft(0);
				mPlayer1.setRight(20);
			}
			mClicked = true;
			break;
		case R.id.btn_right:
			//if (mClicked) return;
			left = mPlayer1.getLeft();
			right = mPlayer1.getRight();
			
			if (768 - PLAYER_MOVEMENT_PIXEL >= right) {
				mPlayer1.setLeft(left+PLAYER_MOVEMENT_PIXEL);
				mPlayer1.setRight(right+PLAYER_MOVEMENT_PIXEL);
			} else {
				mPlayer1.setLeft(748);
				mPlayer1.setRight(768);
			}
			mClicked = true;
			break;
		}
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		Log.d(TAG,"onLockClick");
		int left;
		int right;
		switch(v.getId()) {
		case R.id.btn_left:
			//if (mClicked) return;
			left = mPlayer1.getLeft();
			right = mPlayer1.getRight();
			
			if (PLAYER_MOVEMENT_PIXEL <= left) {
				mPlayer1.setLeft(left-PLAYER_MOVEMENT_PIXEL);
				mPlayer1.setRight(right-PLAYER_MOVEMENT_PIXEL);
			} else {
				mPlayer1.setLeft(0);
				mPlayer1.setRight(20);
			}
			mClicked = true;
			break;
		case R.id.btn_right:
			//if (mClicked) return;
			left = mPlayer1.getLeft();
			right = mPlayer1.getRight();
			
			if (768 - PLAYER_MOVEMENT_PIXEL >= right) {
				mPlayer1.setLeft(left+PLAYER_MOVEMENT_PIXEL);
				mPlayer1.setRight(right+PLAYER_MOVEMENT_PIXEL);
			} else {
				mPlayer1.setLeft(748);
				mPlayer1.setRight(768);
			}
			mClicked = true;
			break;
		}
		return false;
	}

}