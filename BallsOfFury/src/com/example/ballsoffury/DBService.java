package com.example.ballsoffury;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class DBService extends AsyncTask<String, Object, Object> {

	@Override
	protected Object doInBackground(String... params) {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://web.engr.illinois.edu/~chuench1/temp/testfile.txt");
		HttpResponse response = null;
		
		try {
			StringEntity stringEntity = new StringEntity(params[0]);
			httppost.setEntity(stringEntity);
			response = httpclient.execute(httppost);
			return EntityUtils.toString(response.getEntity());
		} catch (UnsupportedEncodingException e1) {
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
