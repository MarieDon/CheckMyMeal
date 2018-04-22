package ie.app.checkmymeal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ie.app.checkmymeal1.Database.DatabaseHandler;
import ie.app.checkmymeal1.Models.Meal;

public class Meals extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference dbRef;
    private List<Meal> MealList;
    private ArrayAdapter spinner;
    private TextView nameEntry, calsGender;
    private Spinner breakfastSpinner, lunchSpinner, dinnerSpinner, snackSpinner, snackArray2;
    private Button addMeals;
    private Button viewMeals;
    private int cals = 0;
    private Button Test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        nameEntry = findViewById(R.id.nameEntry);
        calsGender = findViewById(R.id.calGender);
        breakfastSpinner = findViewById(R.id.breakfastSpinner);
        lunchSpinner = findViewById(R.id.lunchSpinner);
        dinnerSpinner = findViewById(R.id.dinnerSpinner);
        snackSpinner = findViewById(R.id.snackSpinner);
        snackArray2 = findViewById(R.id.snackArray2);
        addMeals = findViewById(R.id.addMeals);
        viewMeals = findViewById(R.id.viewMeals);
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference().child("Meal_Table");

        Test = findViewById(R.id.button);



        //Breakfast
        spinner = ArrayAdapter.createFromResource(Meals.this, R.array.BreakfastArray, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breakfastSpinner.setAdapter(spinner);

        //Lunch
        spinner = ArrayAdapter.createFromResource(Meals.this, R.array.LunchArray, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lunchSpinner.setAdapter(spinner);

        //Dinner
        spinner = ArrayAdapter.createFromResource(Meals.this, R.array.DinnerArray, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dinnerSpinner.setAdapter(spinner);

        //Snack1
        spinner = ArrayAdapter.createFromResource(Meals.this, R.array.SnackArray, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snackSpinner.setAdapter(spinner);

        //Snack1
        spinner = ArrayAdapter.createFromResource(Meals.this, R.array.SnackArray2, android.R.layout.simple_spinner_item);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snackArray2.setAdapter(spinner);


        Intent intent = getIntent();
        nameEntry.setText(intent.getStringExtra("name"));
        String gender = intent.getStringExtra("gender");
//        if (gender.equals("Male")) {
//            cals = 2500;
//        } else {
//            cals = 2000;
//        }


        calsGender.setText("You should have " + String.valueOf(cals) + " calories a day");



        addMeals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                saveMealToDB(v);
            }
        });

        viewMeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Meals.this, MealPlans.class);
               startActivity(intent);
            }
        });
    }



    public void saveMealToDB(View v){
        int Calories=0;
        Meal meal = new Meal();
        String breakfast = breakfastSpinner.getSelectedItem().toString();
        String lunch = lunchSpinner.getSelectedItem().toString();
        String dinner = dinnerSpinner.getSelectedItem().toString();
        String snack1 = snackSpinner.getSelectedItem().toString();
        String snack2 = snackArray2.getSelectedItem().toString();
        Log.i("Marie", "mycals"+String.valueOf(cals));


        switch(breakfast)
        {
            case "Porridge - 50cals": Calories=50;
            cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Breakfast Calories", "mycals"+String.valueOf(cals));
                break;

            case "Cereal - 125cals": Calories=125;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Avocado on Toast - 150cals": Calories=150;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Scrambled Egg - 150cals": Calories=150;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Smoothie - 50cals": Calories=50;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;
        }

        switch(lunch)
        {
            case "Salad - 150cals": Calories=150;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(cals));
                break;

            case "Turkey Sandwich - 150cals": Calories=150;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Soup - 80cals": Calories=80;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Chicken and Pasta - 350cals": Calories=350;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Salmon with Asparagus - 250cals": Calories=250;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;
        }

        switch(dinner)
        {
            case "Chicken with Broccoli - 250cals": Calories=250;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(cals));
                break;

            case "Steak with Veggies - 800cals": Calories=800;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Fish with Potatoes - 500cals": Calories=500;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Chicken and Rice - 500cals": Calories=500;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Turkey with Cous-Cous - 760cals": Calories=760;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;
        }

        switch(snack1)
        {
            case "Veggies - 50cals": Calories=50;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(cals));
                break;

            case "Fruits - 70cals": Calories=70;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Youghurt - 40cals": Calories=40;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Nuts - 180cals": Calories=180;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Protein Bar - 150cals": Calories=150;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;
        }

        switch(snack2)
        {
            case "Veggies - 50cals": Calories=50;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(cals));
                break;

            case "Fruits - 70cals": Calories=70;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Youghurt - 40cals": Calories=40;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Nuts - 180cals": Calories=180;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;

            case "Protein Bar - 150cals": Calories=150;
                cals = cals-Calories;
                calsGender.setText("Calories left to consume " + String.valueOf(cals));
                Log.i("Calories", "mycals"+String.valueOf(Calories));
                break;
        }

        Format format = new SimpleDateFormat("EEEE'-'LLL'-'d k:mm");
        String time = format.format(new Date());

        DatabaseReference newMeal = dbRef.push();
        Map<String, String> savedMeal = new HashMap<>();


        savedMeal.put("Time", time);
        savedMeal.put("Breakfast", breakfast);
        savedMeal.put("Lunch", lunch);
        savedMeal.put("Dinner", dinner);
        savedMeal.put("Snack1", snack1);
        savedMeal.put("Snack2", snack2);
        newMeal.getRef().child("").setValue(savedMeal);
        Log.i("Firebase", newMeal.toString());

        if(cals==0)
        {
            addMeals.setEnabled(false);
        }

        Log.d("Saved", "Record Inserted");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.meals).setVisible(false);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {

            case R.id.view_meals:
                startActivity(new Intent(Meals.this, MealPlans.class));
                break;

            case R.id.meals:
                item.setVisible(false);
                break;

            case R.id.step_counter:
                startActivity(new Intent(Meals.this,StepCounter.class));
                break;

            case R.id.login:
                startActivity(new Intent(Meals.this, FacebookActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

