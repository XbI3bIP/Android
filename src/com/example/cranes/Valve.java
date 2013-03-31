package com.example.cranes;

import android.content.Context;
import android.widget.ImageButton;

public class Valve extends ImageButton {
	private int row;
	private int col;
	
	public Valve(Context context) {
		super(context);
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setRow(int value)
	{
		row = value;
	}
	
	public void setCol(int value)
	{
		col = value;
	}
}
