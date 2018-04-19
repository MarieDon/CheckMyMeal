package ie.app.checkmymeal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
    private List<Meal> mealList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plans);

        RecyclerView= findViewById(R.id.recyclerView);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = new DatabaseHandler(this);
        mealList = db.getAllMeals();

        for(Meal meal : mealList){
            String log = "ID: " + meal.getId() + " " + meal.getTime() + ""
                    + meal.getBreakfast() + " " + meal.getLunch() + " " + meal.getDinner() +
                    " " + meal.getSnack1() + " " + meal.getSnack2();

            Log.d("Meals Orders", log);

        }

        RecyclerAdapter = new MyListAdapter(this, mealList);
        RecyclerView.setAdapter(RecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.view_meals).setVisible(false);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {

            case R.id.meals:
                startActivity(new Intent(MealPlans.this, Meals.class));
                break;

            case R.id.view_meals:
                item.setVisible(false);
                break;

            case R.id.step_counter:
                startActivity(new Intent(MealPlans.this,StepCounter.class));
                break;

            case R.id.login:
                startActivity(new Intent(MealPlans.this, FacebookActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
