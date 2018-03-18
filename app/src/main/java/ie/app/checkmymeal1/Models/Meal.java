package ie.app.checkmymeal1.Models;

import java.io.Serializable;

public class Meal implements Serializable {
    private int id;
    private String time;
    private String breakfast;
    private String lunch;
    private String dinner;
    private String snack1;
    private String snack2;

    public Meal(){

    }

    public Meal(int id, String time, String breakfast, String lunch, String dinner, String snack1, String snack2)
    {
        this.id= id;
        this.time = time;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snack1 = snack1;
        this.snack2 = snack2;
    }

//    String snack1, String snack2public Meal(String time, String breakfast, String lunch, String dinner, ) {
//        this.time = time;
//        this.breakfast = breakfast;
//        this.lunch = lunch;
//        this.dinner = dinner;
//
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getSnack1() {
        return snack1;
    }

    public void setSnack1(String snack1) {
        this.snack1 = snack1;
    }

    public String getSnack2() {
        return snack2;
    }

    public void setSnack2(String snack2) {
        this.snack2 = snack2;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "time='" + time + '\'' +
                ", breakfast='" + breakfast + '\'' +
                ", lunch='" + lunch + '\'' +
                ", dinner='" + dinner + '\'' +
                ", snack1='" + snack1 + '\'' +
                ", snack2='" + snack2 + '\'' +
                '}';
    }
}


