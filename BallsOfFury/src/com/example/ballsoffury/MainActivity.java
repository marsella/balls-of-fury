package com.example.ballsoffury;


import java.io.InputStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.Session;
import com.facebook.SessionState;

public class MainActivity extends Activity {

	private Session.StatusCallback statusCallback = new SessionStatusCallback();
	public Button buttonLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonLogin = (Button) findViewById(R.id.buttonLogin);
		
		Session session = Session.openActiveSession(this, true, statusCallback);
		
//        Session session = Session.getActiveSession();    
//        if (session == null) {    
//            if (savedInstanceState != null) {    
//                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
//            }    
//            if (session == null) {    
//                session = new Session(getApplicationContext());    
//            }    
//            Session.setActiveSession(session);    
//            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
//            	session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
//            }
//        }        
//             
//        if(session.isOpened()) {
//            openScoreReport();      
//        } else {                  
//            buttonLogin.setOnClickListener(new View.OnClickListener() {
//                                                                           
//                @Override
//                public void onClick(View v) {
//                    onClickLogin(v);              
//                }                      
//            });
//        }

	}

    public void onClickLogin(View v) {    
    	Log.d("DEBUG", "CLICK LOGIN VIEW");
    	Session.openActiveSession(this, true, statusCallback);    
//      
//        Session session = Session.getActiveSession();    
//        if (!session.isOpened() && !session.isClosed()) {
//        	Log.d("DEBUG", "neither open nor closed.");
//            session.openForRead(new Session.OpenRequest(this).setCallback(statusCallback));
//        } else {
//        	Log.d("DEBUG", "this is what we want to see");
//            Session.openActiveSession(this, true, statusCallback);    
//        }    
    }    
    
    void openScoreReport() {  
    	Log.d("DEBUG", "OMG OPEN DAT SCORE REPORT");
    	Toast.makeText(getApplicationContext(), "now open score report", 60).show();
    	/* uncomment when you've pulled ScoreReport    
        Bundle bundle = new Bundle();    
        String[] friends = {"Sally", "Bob"};    
        bundle.putStringArray("friends", friends);    
        Intent intent = new Intent(MainActivity.this, ScoreReport.class);    
        startActivity(intent);    
        */    
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

    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
        	if(session.isOpened()) {
        		openScoreReport();
        	} else {
        		Log.d("DEBUG", "session not opened.");
        	}
        }
    }
}
