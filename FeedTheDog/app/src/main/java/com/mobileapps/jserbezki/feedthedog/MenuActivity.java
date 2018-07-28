package com.mobileapps.jserbezki.feedthedog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity implements OnClickListener {

	TextView textView;

	Button newGameButton;
	Button settingsButton;
	Button helpButton;
	Button aboutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		textView = findViewById(R.id.textView1);

		newGameButton = findViewById(R.id.new_game_button);
		newGameButton.setOnClickListener(this);
		settingsButton = findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(this);
		helpButton = findViewById(R.id.help_button);
		helpButton.setOnClickListener(this);
		aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onResume() {

		super.onResume();
	}

	public void onClick(View view) {
		switch (view.getId()){
			case R.id.new_game_button:
				Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i1);
				break;
			case R.id.settings_button:
				Intent i2 = new Intent(getApplicationContext(), SettingsActivity.class);
				startActivity(i2);
				break;
			case R.id.help_button:
				Intent i3 = new Intent(getApplicationContext(), HelpActivity.class);
				startActivity(i3);
				break;
			case R.id.about_button:
				Intent i4 = new Intent(getApplicationContext(), AboutActivity.class);
				startActivity(i4);
				break;
			default:
				break;
		}
	}
}
