package com.example.hellosmartwatch;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private HashMap<String, String> notifications = new HashMap<String, String>();
	private Object[] crunchifyKeys;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initilize();
		
		new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	
                 Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
            	 Intent serviceIntent = new Intent();
            	 serviceIntent.setClass(getApplicationContext(), HelloExtensionService.class);
		         serviceIntent.setAction(HelloExtensionService.INTENT_ACTION_ADD);
		         serviceIntent.putExtra("name", key.toString());
		         serviceIntent.putExtra("message", notifications.get(key));
		         startService(serviceIntent);
            	
            }
        }, 0, 50000);
	}
	
	private void initilize(){
		notifications.put("APPOINTMENT", "Doctor appointment in one hour");
		notifications.put("APPOINTMENT", "Dentist appointment in one hour");
		notifications.put("BIRTHDAY", "It's Ana's birthday. Say Happy Birthday!");
		notifications.put("BIRTHDAY", "It's Jack's birthday. Say Happy Birthday!");
		notifications.put("BIRTHDAY", "It's Mike's birthday. Say Happy Birthday!");
		notifications.put("MEDICINE", "Take ASPIRIN");
		notifications.put("MEDICINE", "Take LEMSIP");
		notifications.put("MEDICINE", "Take ANTIBIOTICS");
		crunchifyKeys = notifications.keySet().toArray();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		
		return true;
	}

	
	
	public void buttonClicked(View v){
		  if(v.getId() == R.id.btnSend){
		   Intent serviceIntent = new Intent(this, HelloExtensionService.class);
		         serviceIntent.setAction(HelloExtensionService.INTENT_ACTION_ADD);
		         EditText etName = (EditText) findViewById(R.id.etTitle);
		         EditText etMsg = (EditText) findViewById(R.id.etMessage);
		         serviceIntent.putExtra("name", etName.getText().toString());
		         serviceIntent.putExtra("message", etMsg.getText().toString());
		         startService(serviceIntent);
		        
		  }
		  else if(v.getId() == R.id.btnClearn){
		   Intent serviceIntent = new Intent(this, HelloExtensionService.class);
		         serviceIntent.setAction(HelloExtensionService.INTENT_ACTION_CLEAR);
		         startService(serviceIntent);
		  }
		  
		 }
}
