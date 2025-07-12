package jj.zmaj.wpm;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnHelp, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gradient effect for "TypeRush" title
        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.post(() -> {
            int width = tvTitle.getMeasuredWidth();
            if (width > 0) {
                Shader shader = new LinearGradient(
                        0, 0, width, tvTitle.getTextSize(),
                        new int[]{Color.parseColor("#6adcff"), Color.parseColor("#a0a1bd")},
                        null, Shader.TileMode.CLAMP);
                tvTitle.getPaint().setShader(shader);
                tvTitle.invalidate();
            }
        });



        // Start button - launches GameActivity
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });

        // History button - launches HistoryActivity
        Button btnHistory = findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });


        // Help & About buttons
        btnHelp = findViewById(R.id.btnHelp);
        btnAbout = findViewById(R.id.btnAbout);

        btnHelp.setOnClickListener(v -> showHelpDialog());
        btnAbout.setOnClickListener(v -> showAboutDialog());
    }

    private void showHelpDialog() {
        new AlertDialog.Builder(this)
                .setTitle("How to Play TypeRush 🚀")
                .setMessage("• Type the words shown as fast and accurately as you can.\n"
                        + "• Press the spacebar after each word.\n"
                        + "• The rocket rises with each correct word!\n"
                        + "• Speed (WPM), accuracy, and errors are tracked.\n"
                        + "• The time limit is set in Settings.\n\n"
                        + "Tip: Go for both speed and accuracy! 🚀")
                .setPositiveButton("Close", null)
                .show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("About TypeRush")
                .setMessage("Author: Zmaj @ https://jovanhq.tech\n"
                        + "Last Modified Date: 7/4/2025\n\n"
                        + "TypeRush is a fast-paced typing game that tests your speed and accuracy!\n"
                        + "Built in Java, Android Studio.")
                .setPositiveButton("Close", null)
                .show();
    }
}
