package com.example.places;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedList extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.selected);

		TextView name = (TextView) findViewById(R.id.slct_name);
		TextView city = (TextView) findViewById(R.id.slct_city);
		TextView state = (TextView) findViewById(R.id.slct_state);
		Bundle lista = getIntent().getExtras();
		
		String objeto = lista.getString("puxa");
		
		try{
			JSONObject placeHash = new JSONObject(objeto);
			
			String nameP = placeHash.getString("name");
			String cityP = placeHash.getString("city");
			String stateP = placeHash.getString("state");
			
			name.setText(getString(R.string.name, nameP));
			city.setText(getString(R.string.city, cityP));
			state.setText(getString(R.string.state, stateP));
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		//String listName = lista.getString("name");		
		//String listCity = lista.getString("city");
		//String listState = lista.getString("state");
		
		
		
		
	}

}
