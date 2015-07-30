package com.leftpark.android.pangpang;

public class Circle {
	
	private float cx;
	private float cy;
	private float radius;
	private int color;
	
	private float mx = 5;
	private float my = 5;
	
	public Circle(float cx, float cy, float radius) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
	}
	
	public Circle(float cx, float cy, float radius, int color) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
		this.color = color;
	}
	
	public void setValues(float cx, float cy, float radius) {
		this.cx = cx;
		this.cy = cy;
		this.radius = radius;
	}
	
	public void setCX(float cx) {
		this.cx = cx;
	}
	
	public float getCX() {
		return this.cx;
	}
	
	public void setCY(float cy) {
		this.cy = cy;
	}
	
	public float getCY() {
		return this.cy;
	}
	
	public float getRadius() {
		return this.radius;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public void setMX(float mx) {
		this.mx = mx;
	}
	
	public float getMX() {
		return this.mx;
	}
	
	public void setMY(float my) {
		this.my = my;
	}
	
	public float getMY() {
		return this.my;
	}
}
