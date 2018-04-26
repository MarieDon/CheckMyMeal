package ie.app.checkmymeal1.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import ie.app.checkmymeal1.R;

/**
 * Created by marie on 23/04/2018.
 */

public class MealsViewHolder extends RecyclerView.ViewHolder{

    public TextView time, breakfast,
            lunch, dinner,
            snack1, snack2;
    public ImageButton update, delete;

    public MealsViewHolder(View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.timeid);
        breakfast = itemView.findViewById(R.id.breakfastTag);
        lunch = itemView.findViewById(R.id.lunchTag);
        dinner = itemView.findViewById(R.id.dinnerTag);
        snack1 = itemView.findViewById(R.id.snack1Tag);
        snack2 = itemView.findViewById(R.id.snack2Tag);
        update= itemView.findViewById(R.id.updateButton);
        delete = itemView.findViewById(R.id.deleteButton);

    }
}
