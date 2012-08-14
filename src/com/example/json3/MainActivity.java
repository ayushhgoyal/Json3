package com.example.json3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
	List<NameValuePair> params;
final static String URL = "http://192.168.1.9/sumit/hello.php";
	//final static String URL = "http://json.org/example.html";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv1);
		client = new DefaultHttpClient();
		
		Log.e("my","check");
		

		
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "Hola"));
		params.add( new BasicNameValuePair("msg", "Hooah"));
		
		new Read().execute("user");
	
	}
	
	public JSONObject sendJSONtoUrl() throws ClientProtocolException, IOException, JSONException{
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		try {
			httpPost.setEntity( new UrlEncodedFormEntity(params));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		HttpResponse r = client.execute(httpPost);
		int status = r.getStatusLine().getStatusCode();
		if (status == 200) {
			Log.e("my","status verified");
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);

			//This works fine--------------------
						JSONArray datastream1 = new JSONArray(data);
			JSONObject message = datastream1.getJSONObject(0);
			
			return message;
		
		}
		else{
		return null;
		
		}
		
		
		
		
		///++++++++++++++
	}

	class Read extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				json = sendJSONtoUrl();
				
									return json.getString(params[0]);
//				String s = myData();
//				return s;
				
				
			} 
//			catch (ClientProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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