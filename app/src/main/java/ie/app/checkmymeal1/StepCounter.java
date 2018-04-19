package ie.app.checkmymeal1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

    public class StepCounter extends AppCompatActivity implements SensorEventListener{
    TextView steps;
    SensorManager sensorManager;
    private Sensor StepCounter;
    private Sensor StepDetector;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_step_counter);

    steps = (TextView) findViewById(R.id.steps);

    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    StepCounter = sensorManager.getDefaultSensor (Sensor.TYPE_STEP_COUNTER);
    StepDetector = sensorManager.getDefaultSensor (Sensor.TYPE_STEP_DETECTOR);
}

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, StepCounter,

                SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, StepDetector,

                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this, StepCounter);
        sensorManager.unregisterListener(this, StepDetector);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;


        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            steps.setText("Total: " + value);
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            steps.setText( value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareOptionsMenu(Menu menu) {
            super.onPrepareOptionsMenu(menu);
            menu.findItem(R.id.step_counter).setVisible(false);
            return  true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();

            switch(id) {

                case R.id.view_meals:
                    startActivity(new Intent(StepCounter.this, MealPlans.class));
                    break;

                case R.id.meals:
                    startActivity(new Intent(StepCounter.this, Meals.class));
                    break;

                case R.id.step_counter:
                    item.setVisible(false);
                    break;

                case R.id.login:
                    startActivity(new Intent(StepCounter.this, FacebookActivity.class));
                    break;
            }
            return super.onOptionsItemSelected(item);
        }
}
