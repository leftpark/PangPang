package com.leftpark.android.pangpang;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class FigureView extends View{
	private static final String TAG = "MainActivity";
	
	private static final int CIRCLE_MOVEMENT_PIXEL = 3;
	
	private ArrayList<Circle> mCircles = new ArrayList<Circle>();
	private ArrayList<Rectangle> mRectangles = new ArrayList<Rectangle>();
	
	private Rectangle mPlayer1;
	
	private int mLayoutWidth = 768;
	private int mLayoutHeight = 253; //353;
	
	public FigureView(Context context) {
		super(context);
	}
	
	public FigureView(Context context, ArrayList<Circle> circles) {
		super(context);
		this.mCircles = circles;
	}
	
	public FigureView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public FigureView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setCircles(ArrayList<Circle> circles) {
		mCircles = circles;
	}
	
	public void setRectangles(ArrayList<Rectangle> rectangles) {
		mRectangles = rectangles;
	}
	
	public void setPlayer1(Rectangle rectangle) {
		mPlayer1 = rectangle;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextSize(22);
		paint.setAntiAlias(true);
		
		// Circle
		for (Circle c: mCircles) {
			float radius = c.getRadius();
			checkCrash(mCircles);
			isBump(c);
			paint.setColor(c.getColor());
			canvas.drawCircle(c.getCX(), c.getCY(), radius, paint);
		}
		
		// Rectangle
		for (Rectangle r: mRectangles) {
			canvas.drawRect(r.getLeft(), r.getTop(), r.getRight(), r.getBotton(), paint);
		}
		
		// Player
		canvas.drawRect(mPlayer1.getLeft(), mPlayer1.getTop(), mPlayer1.getRight(), mPlayer1.getBotton(), paint);
		
		// Stroke
		//paint.setStrokeWidth(2);
		//canvas.drawLine(500, 200, 600, 200, paint);
	}
	
	public void callCircleDraw(ArrayList<Circle> circles) {
		mCircles = circles;
		invalidate();
	}
	
	private boolean isBump(Circle circle) {
		boolean isBump = false;
		
		float cx = circle.getCX();
		float cy = circle.getCY();
		float radius = circle.getRadius();
		
		float left = cx-radius;
		float right = cx+radius;
		float top = cy-radius;
		float bottom = cy+radius;
		
		//Log.d(TAG,"left = "+left+", right = "+right+", top = "+top+", bottmo = "+bottom);
		
		float mx = circle.getMX();
		float my = circle.getMY();
		
		//Log.d(TAG,"mx = "+mx+", my = "+my);
		
		// ===============================
		// check wit Layout
		if (0 >= left || mLayoutWidth <= right) {
			circle.setMX(-mx);
			isBump = true;
		}
		circle.setCX(cx+circle.getMX());
		
		if (30 >= top || mLayoutHeight <= bottom) {
			circle.setMY(-my);
		}
		circle.setCY(cy+circle.getMY());
		// ===============================
		
		return isBump;
	}
	
	private void checkCrash(ArrayList<Circle> circles) {
		int cnt = circles.size() - 1;
		//Log.d(TAG,"cnt = "+cnt);
		if (cnt == 0) return;
		
		for (int i = 0; i < cnt; i++) {
			//Log.d(TAG,"i = "+i);
			//Circle c1 = circles.get(i);
			for (int j = i+1; j <= cnt; j++) {
				/*Log.d(TAG,"j = "+j);
				Circle c2 = circles.get(j);
				if (isCrashed(c1, c2)) {
					mCircles.remove(j);
				}*/
			}
		}
	}
	
	private boolean isCrashed(Circle c1, Circle c2) {
		boolean isCrashed = false;
		
		float cx1 = c1.getCX();
		float cy1 = c1.getCY();
		float cx2 = c2.getCX();
		float cy2 = c2.getCY();
		
		float x_len = Math.abs(cx1-cx2);
		float y_len = Math.abs(cy1-cy2);
		double z_len = Math.sqrt(Math.pow(x_len, 2) + Math.pow(y_len, 2));
		
		Log.d(TAG,"x = "+x_len+", y = "+y_len+", z = "+z_len);
		
		if (z_len < c1.getRadius() || z_len < c2.getRadius()) {
			isCrashed = true;
		}
		
		return isCrashed;
	}
	
	public void setLinearLayout(LinearLayout layout) {
		mLayoutWidth = layout.getWidth();
		mLayoutHeight = layout.getHeight();
	}

}
