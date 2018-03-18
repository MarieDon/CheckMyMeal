package ie.app.checkmymeal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ie.app.checkmymeal1.Database.DatabaseHandler;
import ie.app.checkmymeal1.Models.Meal;

public class Meals extends AppCompatActivity {
    private List<Meal> MealList;
    private ArrayAdapter spinner;
    private TextView nameEntry, calsGender;
    private Spinner breakfastSpinner, lunchSpinner, dinnerSpinner, snackSpinner, snackArray2;
    private Button addMeals;
    private Button viewMeals;
    private int cals = 0;
    private DatabaseHandler db;


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

        db = new DatabaseHandler(this) ;

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
        if (gender.equals("Male")) {
            cals = 2500;
        } else {
            cals = 2000;
        }
        calsGender.setText("You should have " + String.valueOf(cals) + " calories a day");



        addMeals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
//                Meal meal = new Meal();
//                String text = breakfastSpinner.getSelectedItem().toString();
//                String text1 = lunchSpinner.getSelectedItem().toString();
//                String text2 = dinnerSpinner.getSelectedItem().toString();
//                String text3 = snackSpinner.getSelectedItem().toString();
//                String text4 = snackArray2.getSelectedItem().toString();
//
//                Format format = new SimpleDateFormat("MM'/'dd'/'y hh:mm");
//                String time = format.format(new Date());
//
//                db.addOrder(new Meal(text, text1, text2, text3));
//
//                Log.d("", "Record Inserted");
                saveMealToDB(v);
                Toast.makeText(Meals.this,"Meals have been added", Toast.LENGTH_LONG).show();
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

    private void saveMealToDB(View v){
        Meal meal = new Meal();
        String text = breakfastSpinner.getSelectedItem().toString();
        String text1 = lunchSpinner.getSelectedItem().toString();
        String text2 = dinnerSpinner.getSelectedItem().toString();
        String text3 = snackSpinner.getSelectedItem().toString();
        String text4 = snackArray2.getSelectedItem().toString();

        Format format = new SimpleDateFormat("EEEE'-'LLL'-'d k:mm");
        String time = format.format(new Date());

        meal.setTime(time);
        meal.setBreakfast(text);
        meal.setLunch(text1);
        meal.setDinner(text2);
        meal.setSnack1(text3);
        meal.setSnack2(text4);

        db.addMeal(meal);

        Log.d("Saved", "Record Inserted");



    }
}

