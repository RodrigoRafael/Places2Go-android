package com.example.places;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListPlace extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listpl);

		ListView name = (ListView) findViewById(R.id.name_list);

		final ArrayList<String> myList = new ArrayList<String>();

		ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll()
				.build();
		StrictMode.setThreadPolicy(policy);

		final String response = makeRequest("http://192.168.0.13:3000/places.json");

		try {

			JSONArray json = new JSONArray(response);

			for (int i = 0; i < json.length(); i++) {
				String n = json.getJSONObject(i).getString("name");
				myList.add(n);
			}

		} catch (JSONException e) {
			e.printStackTrace();

		}

		ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, myList);
		name.setAdapter(myarrayAdapter);

		name.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				JSONArray json;
				try {
					json = new JSONArray(response);
					String placeString = json.getJSONObject(position).toString();
					//String n = json.getJSONObject(position).getString("name");
					//String c = json.getJSONObject(position).getString("city");
					//String s = json.getJSONObject(position).getString("state");

					Intent intent = new Intent(ListPlace.this,
							SelectedList.class);
					intent.putExtra("puxa", placeString);
					//intent.putExtra("city", c);
					//.putExtra("state", s);
					startActivity(intent);
				} catch (JSONException e) {

					e.printStackTrace();
				}

			}
		});
	}

	private String makeRequest(String urlAddress) {
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return response;
	}

	private String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();

		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return builder.toString();
	}

}
