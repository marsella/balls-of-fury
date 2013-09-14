package com.example.ballsoffury;


import java.io.InputStream;
import java.util.Scanner;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void foo(View v){
		
		InputStream is = getResources().openRawResource(R.raw.fb);
		
		String str = "";
		Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
		if (scanner.hasNext())
			str = scanner.next();
				
        Intent intent = new Intent(this, ScoreReport.class);
        intent.putExtra("friends", str);
        
        startActivity(intent);
	}

}
