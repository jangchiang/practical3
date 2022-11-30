package theeradon.cp3406.stopwatchapp;

import androidx.annotation.NonNull;
import java.util.Locale;

public class Stopwatch
{
    private int hours;
    private int minutes;
    private int seconds;


    public Stopwatch(int hours, int minutes, int seconds)
    {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Stopwatch()
    {
        this(0,0,0);
    }

    public Stopwatch(String[] times)
    {
        this(Integer.parseInt(times[0]), Integer.parseInt(times[1]), Integer.parseInt(times[2]));
    }

    @Override
    public String toString()
    {
        return String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void tick()
    {
        seconds++;
        if(seconds % 60 == 0)
        {
            seconds = 0;
            minutes++;

            if(minutes % 60 == 0)
            {
                minutes = 0;
                hours++;

                if(hours > 99)
                {
                    hours = 0;
                }
            }
        }
    }
}
