package ie.app.checkmymeal1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import ie.app.checkmymeal1.Database.DatabaseHandler;
import ie.app.checkmymeal1.Models.Meal;

public class Home extends AppCompatActivity {

    private Button nextButton;
    private RadioGroup genderGroup;
    private EditText   nameText;
    private EditText weight;
    private EditText height;
    private int cals = 0;

    private String genderOption;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nextButton= findViewById(R.id.nextButton);
        genderGroup= findViewById(R.id.genderGroup);
        nameText= findViewById(R.id.nameText);



        Log.d("Saved", "Record Inserted");



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get users gender
                int gender = genderGroup.getCheckedRadioButtonId();
                if (gender==R.id.male)
                {
                    genderOption= "Male";
                    cals = 2500;
                }
                else {
                    genderOption="Female";
                    cals = 2000;
                }

                Log.i("Gender", genderOption + " " +cals);
                String Name= nameText.getText().toString();


                Intent intent= new Intent(Home.this, Meals.class);
                intent.putExtra("name", Name);
                intent.putExtra("gender", genderOption);
                startActivity(intent);

            }
        });
    }

}