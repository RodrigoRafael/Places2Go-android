package com.example.places;

import java.io.IOException;

import us.monoid.web.Resty;
import static us.monoid.web.Resty.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddPlace extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addpl);
		
		  ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	         StrictMode.setThreadPolicy(policy);

		final EditText nameEdit = (EditText) findViewById(R.id.name_edit_text);
		final EditText cityEdit = (EditText) findViewById(R.id.city_edit_text);
		final EditText stateEdit = (EditText) findViewById(R.id.state_edit_text);


		Button save = (Button) findViewById(R.id.save_btn);
		

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					String name = nameEdit.getEditableText().toString();
					String city = cityEdit.getEditableText().toString();
					String state = stateEdit.getEditableText().toString();
					
					
					Resty resty = new Resty();
					resty.json("http://192.168.0.13:3000/places", 
							form(data("place[name]", name),
									data("place[city]", city),
									data("place[state]", state)));
					Intent voltar = new Intent(AddPlace.this, MainActivity.class);
					startActivity(voltar);
					
					
					
					
				}catch 
					(IOException e){
						e.printStackTrace();
					}
				}
			
		});
	}
}