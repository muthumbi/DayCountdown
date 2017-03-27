package com.muthumbi.daycountdown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public TextView countdown;
    public Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        countdown = (TextView) findViewById(R.id.countdown);
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer("DigitalClock");
        Calendar calendar = Calendar.getInstance();

// Get the Current Time
        final Runnable updateTask = new Runnable() {
            public void run() {
                //countdown.setText(getCurrentTimeString()); // shows the current
// time of the day
                countdown.setText(getReminingTime()); // shows the remaining
// time of the day
            }
        };

// update the UI
        int msec = 999 - calendar.get(Calendar.MILLISECOND);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(updateTask);
            }
        }, msec, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
        timer.purge();
        timer = null;
    }

    public String getReminingTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = 23 - calendar.get(Calendar.HOUR_OF_DAY);
        int minute = 59 - calendar.get(Calendar.MINUTE);
        int second = 59 - calendar.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String getCurrentTimeString() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}