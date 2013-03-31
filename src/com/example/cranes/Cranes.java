package com.example.cranes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Cranes extends Activity {
	private int difficult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cranes);

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onFinishButtonClick(v);

            }
        });
        
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onCreditsButtonClick(v);

            }
        });
        
        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.e("debug", "button4 clicked");
            	onLevelsButtonClick(v);

            }
        });
    	
    final Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onStartNew(v);
            }
        }); 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cranes, menu);
        return true;
    }
    
    public void onFinishButtonClick(View view) {
    	this.finish();
    }
  
    public void onStartNew(View view) {
    	Intent i = new Intent(getApplicationContext(), Display_GamescreenActivity.class);
    	       i.putExtra("difficult", difficult);
    	startActivity(i);
    }
    
    public void onCreditsButtonClick(View view) {
    	AlertDialog.Builder popupBuilder = new AlertDialog.Builder(this);
		//TextView myMsg = new TextView(this);
		//myMsg.setText("You Win!");
		//myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
		//myMsg.setTextColor(Color.parseColor("#FFFFFF"));
		popupBuilder.setTitle("Credits");
		popupBuilder.setMessage("Alexey Gorelov\nRuslan Nazmetdinov\nSpecial thanks to:\nNicolas Shatokhin");
		popupBuilder.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int id) {
				dialog.cancel();
			}
		});
		//popupBuilder.setView(myMsg);
		popupBuilder.show();
	    }
  public void onLevelsButtonClick (View view) {
	  Log.e("debug", "onLevelsButtonClick");
	  final String[] difficults = { "3x3", "4x4", "5x5" };
	  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	                      builder.setTitle("Choose dificult level");
	                      builder.setCancelable(false);

				
	                      builder.setNeutralButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								dialog.cancel();
							}
						});

	                      builder.setSingleChoiceItems(difficults, difficult,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int item) {
								Toast.makeText(
										getApplicationContext(),
										"Selected difficult: "
												+ difficults[item],
										Toast.LENGTH_SHORT).show();
								test(item);
							}
						});
	                  builder.create();
	                  builder.show();
  }
  
  private void test(int selected)
  {
	  difficult = selected;
  }
}
