package com.mobileapps.jserbezki.feedthedog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class SettingsActivity extends Activity {

    Spinner durationSpinner;
    Spinner difficultySpinner;

    Button saveButton;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();
        editor.apply();

        durationSpinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(this, R.array.durationArray, android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(durationAdapter);
        int position = prefs.getInt("numberOfDogs", 10);
        switch (position) {
            case 5:
                durationSpinner.setSelection(durationAdapter.getPosition("Very short"));
                break;
            case 10:
                durationSpinner.setSelection(durationAdapter.getPosition("Short"));
                break;
            case 15:
                durationSpinner.setSelection(durationAdapter.getPosition("Normal"));
                break;
            case 20:
                durationSpinner.setSelection(durationAdapter.getPosition("Long"));
                break;
            case 25:
                durationSpinner.setSelection(durationAdapter.getPosition("Very long"));
                break;
            default:
                durationSpinner.setSelection(durationAdapter.getPosition("Short"));
                break;
        }


        difficultySpinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> difficultyAdapter = ArrayAdapter.createFromResource(this, R.array.difficultyArray, android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);
        position = prefs.getInt("minimumShowTime", 750);
        switch (position) {
            case 1000:
                difficultySpinner.setSelection(difficultyAdapter.getPosition("Easy"));
                break;
            case 750:
                difficultySpinner.setSelection(difficultyAdapter.getPosition("Normal"));
                break;
            case 500:
                difficultySpinner.setSelection(difficultyAdapter.getPosition("Hard"));
                break;
            default:
                difficultySpinner.setSelection(difficultyAdapter.getPosition("Normal"));
                break;
        }

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String durationChoice = durationSpinner.getSelectedItem().toString();

                switch (durationChoice) {
                    case "Very short":
                        editor.putInt("numberOfDogs", 5);
                        editor.apply();
                        break;
                    case "Short":
                        editor.putInt("numberOfDogs", 10);
                        editor.apply();
                        break;
                    case "Normal":
                        editor.putInt("numberOfDogs", 15);
                        editor.apply();
                        break;
                    case "Long":
                        editor.putInt("numberOfDogs", 20);
                        editor.apply();
                        break;
                    case "Very long":
                        editor.putInt("numberOfDogs", 25);
                        editor.apply();
                        break;
                    default:
                        editor.putInt("numberOfDogs", 10);
                        editor.apply();
                        break;
                }

                String difficultyChoice = difficultySpinner.getSelectedItem().toString();

                switch (difficultyChoice) {
                    case "Easy":
                        editor.putInt("minimumShowTime", 1000);
                        editor.apply();
                        break;
                    case "Normal":
                        editor.putInt("minimumShowTime", 750);
                        editor.apply();
                        break;
                    case "Hard":
                        editor.putInt("minimumShowTime", 500);
                        editor.apply();
                        break;
                    default:
                        editor.putInt("minimumShowTime", 750);
                        editor.apply();;
                        break;
                }

                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(i);
            }
        });
    }


}
