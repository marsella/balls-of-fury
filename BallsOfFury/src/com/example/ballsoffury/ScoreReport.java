package com.example.ballsoffury;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreReport extends Activity {

	private SeekBar seekBar;
	private SeekBar seekBar2;
	private TextView seekBarValue;
	private TextView seekBarValue2;
	private TextView player1;
	private AutoCompleteTextView player2;
	String[] friends = {"Annie", "Anna", "Emilia", "Emily", "Emile", "Catherine"};
//	String[] friends_id;
	Object[] frnds;
	
	HashMap<String, String> hm = new HashMap<String, String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_report);

		Intent intent = getIntent();
		try {
			JSONObject jsonobj = new JSONObject(intent.getExtras().getString("friends"));
			JSONArray jsonarr = jsonobj.getJSONArray("data");
			for(int i=0;i<jsonarr.length();i++){
				JSONObject friend = jsonarr.getJSONObject(i);
				hm.put(friend.getString("name"), friend.getString("id"));
			}
			friends = hm.keySet().toArray(new String[0]);
		} catch (JSONException e) {
			Toast.makeText(getApplicationContext(), "T T", Toast.LENGTH_LONG).show();
		}
		
		player1 = (TextView) findViewById(R.id.player1);
//		player1.setText(intent.getExtras().getString("player1"));
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, friends);

		player2 = (AutoCompleteTextView) findViewById(R.id.player2);
		player2.setThreshold(1);
		player2.setAdapter(arrayAdapter);
		
		// Seekbar initializations -------------------------------------------------
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBarValue = (TextView) findViewById(R.id.score1);
		seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
		seekBarValue2 = (TextView) findViewById(R.id.score2);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekBarValue.setText(String.valueOf(progress));
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});

		seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				seekBarValue2.setText(String.valueOf(progress));
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		//----------------------------------------------------------------------------
	}

	public void submit(View v){
		
		String request = "{" +
			"\"player1\": \""+ player1.getText() + "\"," +
			"\"player2\": \""+ hm.get(player2.getText().toString()) + "\"," +
			"\"score1\": \""+ seekBarValue.getText() + "\"," +
			"\"score2\": \""+ seekBarValue2.getText() + "\"" +
			"}";
		Toast.makeText(getApplicationContext(), player2.getText(), Toast.LENGTH_LONG).show();
		try {
			String res = (String) new DBService().execute("").get();
//			Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(), request, Toast.LENGTH_LONG).show();
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score_report, menu);
		return true;
	}

}
