package com.leftpark.android.pangpang;

import android.content.Context;

public class Rectangle {
	int left;
	int top;
	int right;
	int bottom;
	
	public Rectangle(int left, int top, int right, int bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getLeft() {
		return this.left;
	}
	
	public void setTop(int top) {
		this.top = top;
	}
	
	public int getTop() {
		return this.top;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	public int getRight() {
		return this.right;
	}
	
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	
	public int getBotton() {
		return this.bottom;
	}
}
