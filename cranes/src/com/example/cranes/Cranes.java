package com.example.cranes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Cranes extends Activity {

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
    	startActivity(i);
    }
  
}
