package ie.app.checkmymeal1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ie.app.checkmymeal1.Models.Meal;
import ie.app.checkmymeal1.Util.Util;

public class DatabaseHandler  {


//    public DatabaseHandler(Context context) {
//        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
//    }
//
//    //create tables
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        //SQL = Structured Query Language
//        String CREATE_MENU_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
//                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
//                + Util.KEY_TIME + " TEXT,"
//                + Util.KEY_BREAKFAST + " TEXT,"
//                + Util.KEY_LUNCH + " TEXT,"
//                + Util.KEY_DINNER + " TEXT,"
//                + Util.KEY_SNACK1 + " TEXT, "
//                + Util.KEY_SNACK2 + " TEXT " + ")";
//
//        db.execSQL(CREATE_MENU_TABLE);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        //Drops table
//        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
//
//        //CREATE TABLE AGAIN
//        onCreate(db);
//    }
//
//    public void addMeal(Meal meal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues value = new ContentValues();
//        value.put(Util.KEY_TIME, meal.getTime());
//        value.put(Util.KEY_BREAKFAST, meal.getBreakfast());
//        value.put(Util.KEY_LUNCH, meal.getLunch());
//        value.put(Util.KEY_DINNER, meal.getDinner());
//        value.put(Util.KEY_SNACK1, meal.getSnack1());
//        value.put(Util.KEY_SNACK2, meal.getSnack2());
//
//        // INSERT TO ROW
//        long insertOrThrow = db.insertOrThrow(Util.TABLE_NAME, null, value);
//        db.close(); //close db connection
//    }
//
//    //Get an Order
//
//    public Meal getMeal(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{
//                        Util.KEY_ID,
//                        Util.KEY_TIME,
//                        Util.KEY_BREAKFAST,
//                        Util.KEY_LUNCH,
//                        Util.KEY_DINNER,
//                        Util.KEY_SNACK1,
//                        Util.KEY_SNACK2},
//                Util.KEY_ID + "=?"
//                , new String[]{String.valueOf(id)}
//                , null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
////        Meal meal = new Meal(Integer.parseInt(
////                cursor.getString(0)),
////                cursor.getString(1),
////                cursor.getString(2),
////                cursor.getString(3),
////                cursor.getString(4),
////                cursor.getString(5),
////                cursor.getString(6));
////
////        return meal;
////    }
//
//
//    public List<Meal> getAllMeals() {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<Meal> mealList = new ArrayList<>();
//
//        //Select all meals
//
//        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
//        Cursor cursor = db.rawQuery(selectAll, null);
//
//        //Loop through our meals
//
//        if (cursor.moveToFirst()) {
//            do {
//                Meal meal = new Meal("monday", breakfast, lunch, dinner, snack1, snack2);
//                meal.setId(Integer.parseInt(cursor.getString(0)));
//                meal.setTime(cursor.getString(1));
//                meal.setBreakfast(cursor.getString(2));
//                meal.setLunch(cursor.getString(3));
//                meal.setDinner(cursor.getString(4));
//                meal.setSnack1(cursor.getString(5));
//                meal.setSnack2(cursor.getString(6));
//                mealList.add(meal);
//            } while (cursor.moveToNext());
//        }
//
//        return mealList;
//    }
//
//
//    //Get meal Count
//    public int getMealCount() {
//        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//
//        //cursor.close();
//
//        return cursor.getCount();
//
//    }
//
//    //update
//    public int updateMeals(Meal meal) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Util.KEY_BREAKFAST, meal.getBreakfast());
//        values.put(Util.KEY_LUNCH, meal.getLunch());
//        values.put(Util.KEY_DINNER, meal.getDinner());
//        values.put(Util.KEY_SNACK1, meal.getSnack1());
//        values.put(Util.KEY_SNACK2, meal.getSnack2());
//
//        //update row
//        return db.update(Util.TABLE_NAME, values,
//                Util.KEY_ID + "=?",
//                new String[]{String.valueOf(meal.getId())});
//    }
//
//    //delete
//    public void deleteMeals(int id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(Util.TABLE_NAME,
//                Util.KEY_ID + "=?",
//                new String[]{String.valueOf(id)});
//        db.close();
//    }
}