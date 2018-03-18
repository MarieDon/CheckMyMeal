package ie.app.checkmymeal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

import ie.app.checkmymeal1.Adapter.MyListAdapter;
import ie.app.checkmymeal1.Database.DatabaseHandler;
import ie.app.checkmymeal1.Models.Meal;

public class MealPlans extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private MyListAdapter RecyclerAdapter;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plans);

        RecyclerView= findViewById(R.id.recyclerView);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHandler(this);
        List<Meal> mealList = db.getAllMeals();

        for(Meal meal : mealList){
            String log = "ID: " + meal.getId() + " " + meal.getTime() + ""
                    + meal.getBreakfast() + " " + meal.getLunch() + " " + meal.getDinner() +
                    " " + meal.getSnack1() + " " + meal.getSnack2();

            Log.d("Meals Orders", log);

        }

        RecyclerAdapter = new MyListAdapter(this, mealList);
        RecyclerView.setAdapter(RecyclerAdapter);

    }
}
