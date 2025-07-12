package jj.zmaj.wpm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Spinner spinner = findViewById(R.id.spinnerTime);
        Button btnSave = findViewById(R.id.btnSave);

        String[] times = {"30 seconds", "60 seconds", "120 seconds"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        spinner.setAdapter(adapter);

        // Set selection to saved time
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        int timeSaved = prefs.getInt("timeLimit", 60);
        spinner.setSelection(timeSaved == 30 ? 0 : timeSaved == 60 ? 1 : 2);

        btnSave.setOnClickListener(v -> {
            int selected = spinner.getSelectedItemPosition();
            int chosenTime = (selected == 0) ? 30 : (selected == 1) ? 60 : 120;
            prefs.edit().putInt("timeLimit", chosenTime).apply();
            finish(); // Go back to main screen
        });
    }
}
