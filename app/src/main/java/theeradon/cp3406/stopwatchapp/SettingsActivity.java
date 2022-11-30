package theeradon.cp3406.stopwatchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    public static final int SETTINGS_REQUEST = 100;

    private SeekBar speedBar;
    private TextView speedDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        speedBar = findViewById(R.id.speed);
        speedDisplay = findViewById(R.id.speedDisplay);

        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                speedDisplay.setText(String.format(Locale.ENGLISH,"%d ms", speedBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        speedDisplay.setText(String.format(Locale.ENGLISH, "%d ms", speedBar.getProgress()));
    }

    public void doneClicked(View view)
    {
        int speed = speedBar.getProgress(); // Value Ranges from 500 ms to 2000 ms. Default at 1000 ms

        Intent intent = new Intent();
        intent.putExtra("speed", speed);
        setResult(RESULT_OK, intent);
        finish();
    }
}