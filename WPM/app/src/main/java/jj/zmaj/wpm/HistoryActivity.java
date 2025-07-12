package jj.zmaj.wpm;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ArrayList;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;

public class HistoryActivity extends AppCompatActivity {

    // Example model for one attempt
    public static class Attempt {
        public int wpm, accuracy, errors;
        public String time; // format mm:ss

        public Attempt(int wpm, int accuracy, int errors, String time) {
            this.wpm = wpm;
            this.accuracy = accuracy;
            this.errors = errors;
            this.time = time;
        }
    }

    private List<Attempt> getHistory() {
        List<Attempt> history = new ArrayList<>();
        try {
            SharedPreferences prefs = getSharedPreferences("history", MODE_PRIVATE);
            String raw = prefs.getString("attempts", "[]");
            org.json.JSONArray arr = new org.json.JSONArray(raw);

            for (int i = 0; i < arr.length(); i++) {
                org.json.JSONObject obj = arr.getJSONObject(i);
                int wpm = obj.optInt("wpm", 0);
                int accuracy = obj.optInt("accuracy", 0);
                int errors = obj.optInt("errors", 0);
                String duration = obj.optString("duration", "-");
                history.add(new Attempt(wpm, accuracy, errors, duration));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return history;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        LinearLayout box = findViewById(R.id.historyBox);
        List<Attempt> attempts = getHistory();
        int idx = 1;
        for (Attempt attempt : attempts) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);

            TextView num = new TextView(this); num.setText(idx + ".");
            num.setTextColor(0xFFB1BAD3); row.addView(num);

            TextView wpm = new TextView(this); wpm.setText(attempt.wpm + " WPM");
            wpm.setTextColor(0xFF87FF9C); wpm.setPadding(30,0,0,0); row.addView(wpm);

            TextView acc = new TextView(this); acc.setText(attempt.accuracy + "%");
            acc.setTextColor(0xFF60C7FF); acc.setPadding(30,0,0,0); row.addView(acc);

            TextView err = new TextView(this); err.setText(attempt.errors + " Err");
            err.setTextColor(0xFFFFD15B); err.setPadding(30,0,0,0); row.addView(err);

            TextView time = new TextView(this); time.setText(attempt.time);
            time.setTextColor(0xFFB1BAD3); time.setPadding(30,0,0,0); row.addView(time);

            box.addView(row);
            idx++;
        }
    }

}
