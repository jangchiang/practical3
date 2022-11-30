package theeradon.cp3406.stopwatchapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class StopwatchActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private boolean isRunning;
    private Stopwatch stopwatch;

    private TextView display;
    private Button toggle;

    private int speed;

    public StopwatchActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        toggle = findViewById(R.id.toggle);

        isRunning = false;

        if(savedInstanceState == null)
        {
            stopwatch = new Stopwatch();
            speed = 1000;
        }
        else
        {
            stopwatch = new Stopwatch(savedInstanceState.getString("display").split(":"));
            speed = savedInstanceState.getInt("speed");

            boolean running = savedInstanceState.getBoolean("running");

            if(running)
            {
                enableStopwatch();
            }
        }
        display.setText(stopwatch.toString());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("display", display.getText().toString());
        outState.putBoolean("running", isRunning);
        outState.putInt("speed", speed);
    }

    private void enableStopwatch()
    {
        isRunning = true;
        toggle.setText("stop");

        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                if(isRunning)
                {
                    stopwatch.tick();
                    display.setText(stopwatch.toString());

                    handler.postDelayed(this, speed);
                }
            }
        });
    }

    private void disableStopwatch()
    {
        isRunning = false;
        toggle.setText("start");
    }

    public void buttonClicked(View view)
    {
        if (isRunning)
        {
            disableStopwatch();
        }
        else
        {
            enableStopwatch();
        }
    }

    public void settingsClicked(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SettingsActivity.SETTINGS_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                if(data != null)
                {
                    speed = data.getIntExtra("speed", 1000);
                }
            }
        }
    }

}