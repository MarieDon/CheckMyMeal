package ie.app.checkmymeal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

import ie.app.checkmymeal1.Adapter.MealsViewHolder;
import ie.app.checkmymeal1.Adapter.MyListAdapter;
import ie.app.checkmymeal1.Database.DatabaseHandler;
import ie.app.checkmymeal1.Models.Meal;

public class MealPlans extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private FirebaseRecyclerAdapter<Meal, MealsViewHolder> adapter;

    private List<Meal> mealList;

    private DatabaseReference mDatabase;
    private FirebaseDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plans);

        db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("Meal_Table");

        recyclerView= findViewById(R.id.recyclerView);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);


        adapter = new FirebaseRecyclerAdapter<Meal, MealsViewHolder>(Meal.class,
                R.layout.meal_list_row, MealsViewHolder.class, mDatabase) {

            @Override
            protected void populateViewHolder(MealsViewHolder viewHolder, Meal model, final int position) {

                 viewHolder.time.setText("Time: " + model.getTime());
                 viewHolder.breakfast.setText("Breakfast: " + model.getBreakfast());
                 viewHolder.lunch.setText("Lunch: " + model.getLunch());
                 viewHolder.dinner.setText("Dinner: " + model.getDinner());
                 viewHolder.snack1.setText("Snack1: " + model.getSnack1());
                 viewHolder.snack2.setText("Snack2: " + model.getSnack2());
                 viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         adapter.getRef(position).removeValue();
                     }
                 });
            }
        };
        recyclerView.setAdapter(adapter);
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


        private void deleteMeal(int ID) {
            mDatabase.child(String.valueOf(ID)).removeValue();
            //notifyItemRemoved();
        }
}
