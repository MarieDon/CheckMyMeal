package ie.app.checkmymeal1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import ie.app.checkmymeal1.Database.DatabaseHandler;
import ie.app.checkmymeal1.Meals;
import ie.app.checkmymeal1.Models.Meal;
import ie.app.checkmymeal1.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private Context context;
    public List<Meal> mealList;


    public MyListAdapter(Context context, List mealList) {
        this.context=context;
        this.mealList=mealList;
    }


    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_list_row, parent, false);
        return new ViewHolder(view, context);
    }


    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.time.setText(meal.getTime());
        holder.breakfast.setText(meal.getBreakfast());
        holder.lunch.setText(meal.getLunch());
        holder.dinner.setText(meal.getDinner());
        holder.snack1.setText(meal.getSnack1());
        holder.snack2.setText(meal.getSnack2());

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView time, breakfast,
                        lunch, dinner,
                        snack1, snack2;
        public ImageButton update, delete;
        public int ID;
        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            time = itemView.findViewById(R.id.timeid);
            breakfast = itemView.findViewById(R.id.breakfastTag);
            lunch = itemView.findViewById(R.id.lunchTag);
            dinner = itemView.findViewById(R.id.dinnerTag);
            snack1 = itemView.findViewById(R.id.snack1Tag);
            snack2 = itemView.findViewById(R.id.snack2Tag);
            update= itemView.findViewById(R.id.updateButton);
            delete = itemView.findViewById(R.id.deleteButton);
            update.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.updateButton:
                    int Position = getAdapterPosition();
                    Meal meal = mealList.get(Position);
                   // editMeal(meal);
                    break;

                case R.id.deleteButton:
                    Position = getAdapterPosition();
                     meal = mealList.get(Position);
                     deleteMeal(meal.getId());
                    break;
            }
        }

        private void deleteMeal(int ID) {
            DatabaseHandler db = new DatabaseHandler(context);
            db.deleteMeals(ID);
            mealList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }

    }
}


