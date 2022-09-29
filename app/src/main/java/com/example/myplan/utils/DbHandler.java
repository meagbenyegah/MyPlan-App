package com.example.myplan.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myplan.pojo.Plan;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "myPlan.db";
    private static final int DATABASE_Version = 4;
    private static final String TABLE_PLAN = "user_plan";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_DESCRIPTION = "description";
    private static final String COL_DATE = "up_date";
    private static final String COL_TIME = "up_time";
    private static final String COL_REPEAT = "repeat";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_DRAW = "CREATE TABLE " + TABLE_PLAN + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TITLE + " TEXT,"
                + COL_DESCRIPTION + " TEXT,"
                + COL_DATE + " DATETIME,"
                + COL_TIME + " TEXT,"
                + COL_REPEAT + " INTEGER"+ ")";

        db.execSQL(CREATE_TABLE_DRAW);

    }

    public boolean addPlan(String title, String description, String date, String time,
                           String repeat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, title);
        cv.put(COL_DESCRIPTION, description);
        cv.put(COL_DATE, date);
        cv.put(COL_TIME, time);
        cv.put(COL_REPEAT, repeat);

        db.insert(TABLE_PLAN,null,cv);
        return true;
    }

    @SuppressLint("Range")
    public List<Plan> getPlans(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM "+ TABLE_PLAN +
                        " ORDER BY " + COL_ID +" DESC LIMIT "+limit,
                null );
        List<Plan> items = new ArrayList<Plan>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                 String title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                 String description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION));
                 String date = cursor.getString(cursor.getColumnIndex(COL_DATE));
                 String time = cursor.getString(cursor.getColumnIndex(COL_TIME));
                 String repeat = cursor.getString(cursor.getColumnIndex(COL_REPEAT));

                //Date d = Config.stringToDate(date);
                //DateFormat dateFormat = new SimpleDateFormat("MMM.dd.yyyy");

                //date = dateFormat.format(d);
                items.add(new Plan(title,description,date,time,repeat));
                cursor.moveToNext();
            }
        }
        cursor.close();

        return items;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
