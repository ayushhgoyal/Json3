package com.example.json3;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView tv;
	HttpClient client;
	JSONObject json;
final static String URL = "http://192.168.1.9/sumit/hello.php";
	//final static String URL = "http://json.org/example.html";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv1);
		client = new DefaultHttpClient();
		
		Log.e("my","check");
		new Read().execute("tag");
//		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//				.detectAll().penaltyLog().build();
//		StrictMode.setThreadPolicy(policy);

	}

	public String myData() throws ClientProtocolException, IOException,
			JSONException {

		StringBuilder url = new StringBuilder(URL);
		HttpGet get = new HttpGet(url.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if (status == 200) {
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
//			JSONArray datastream = new JSONArray(data);
//			JSONObject message = datastream.getJSONObject(0);
//			return message;
			return data;
		} else {
			Toast.makeText(MainActivity.this, "error encountered",
					Toast.LENGTH_SHORT).show();
			return null;
		}

	}

	class Read extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				//json = myData();
				
								//	return json.getString(params[0]);
				String s = myData();
				return s;
				
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			tv.setText(result);

		}

	}

}
