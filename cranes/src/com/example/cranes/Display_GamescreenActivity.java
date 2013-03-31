package com.example.cranes;

import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Display_GamescreenActivity extends Activity {
	private int[][] valveValues;
	private Valve[][] btnArray;
	private Uri valveOpened;
	private Uri valveClosed;
	private int size;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_gamescreen);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Random rand = new Random();
		//int r = rand.nextInt(2);
		
		size = 2;
		btnArray = new Valve[size][size];
		valveValues=new int [size][size];
		
		
		valveOpened = Uri.parse("android.resource://com.example.cranes/drawable/valve_opened");
		valveClosed = Uri.parse("android.resource://com.example.cranes/drawable/valve_closed");
		
		ViewGroup layout = (ViewGroup) findViewById(R.id.gameLayout);
		
	    TableLayout table = new TableLayout(this);
		for (int i=0; i<size;i++)
			
		{
			TableRow row = new TableRow(this);
			for (int j=0; j<size;j++)
				{
		        valveValues[i][j] = rand.nextInt(2);
				btnArray[i][j] = new Valve(this);
                if (valveValues[i][j] == 0) 
                {
                	btnArray[i][j].setImageURI(valveOpened);
				}
                else { 
                	btnArray[i][j].setImageURI(valveClosed);
                }
                
				btnArray[i][j].setRow(i);
				btnArray[i][j].setCol(j);

				btnArray[i][j].setOnClickListener(new View.OnClickListener() {
		            public void onClick(View v) {
		            	onValveClick(v);

		            }
		        });
				
				row.addView(btnArray[i][j]); 	
			}
			table.addView(row);
		}
		layout.addView(table);
	}
	private int invert(int Value)
	{
		if (Value == 0)
			return 1;
		else
			return 0;
	}
	public void onValveClick(View v)
	{
		Valve button = (Valve) v;
		
		int row = button.getRow();
		int col = button.getCol();

		valveValues[row][col]=invert(valveValues[row][col]);
		
		
		for (int i=0; i<size; i++) 
		{
			valveValues[i][col]=invert (valveValues[i][col]);
			 if (valveValues[i][col] == 0) 
	         {
	         	btnArray[i][col].setImageURI(valveOpened);
				}
	         else { 
	         	btnArray[i][col].setImageURI(valveClosed);
	         }
		}
		   for (int j=0; j<size; j++)
		   {
			   valveValues[row][j]=invert (valveValues[row][j]); 
			   if (valveValues[row][j] == 0) 
		         {
		         	btnArray[row][j].setImageURI(valveOpened);
					}
		         else { 
		         	btnArray[row][j].setImageURI(valveClosed);
		   }
			   
		   }
		
		if(isVictory())
		{
			
			AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
			TextView myMsg = new TextView(this);
			//myMsg.setText("You Win!");
			//myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
			//myMsg.setTextColor(Color.parseColor("#FFFFFF"));
			popupBuilder.setTitle("Congratulations!");
			popupBuilder.setMessage("You Win!");
			popupBuilder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,
						int id) {
					dialog.cancel();
					exitGame();
				}
			});
			popupBuilder.setView(myMsg);
			popupBuilder.show();
		}
	}
	
	private Boolean isVictory()
	{
		for (int i=0; i<size; i++) 
		{
		for (int j=0; j<size; j++)
		{
			if (valveValues[i][j] == 1)
					return false;
		}
		}
		return true;
	}

	private void exitGame()
	{
		this.finish();
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display__gamescreen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
