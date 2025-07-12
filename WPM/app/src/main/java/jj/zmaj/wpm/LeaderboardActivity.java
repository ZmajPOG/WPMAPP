package jj.zmaj.wpm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LeaderboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        TextView tvScores = findViewById(R.id.tvScores);

        SharedPreferences prefs = getSharedPreferences("scores", MODE_PRIVATE);
        String[] entries = prefs.getString("history", "").split(";");
        StringBuilder sb = new StringBuilder();
        sb.append("WPM   ACC   ERR\n");
        for (String entry : entries) {
            if (!entry.isEmpty()) {
                String[] parts = entry.split(",");
                if (parts.length >= 3) {
                    sb.append(parts[0]).append("    ");
                    sb.append(parts[1]).append("%    ");
                    sb.append(parts[2]).append("\n");
                }
            }
        }
        tvScores.setText(sb.toString());
    }
}
