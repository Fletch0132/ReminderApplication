package apps.nixonblkmnd.reminderapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import apps.nixonblkmnd.reminderapplication.Formats.FormatDate;

public class DatabaseHelper extends SQLiteOpenHelper {

    DatabaseHelper databaseHelper;

    //DATABASE VARIABLES
    private Context context;
    private static final String DATABASE_NAME = "ReminderApplication.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "reminders";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "rem_name";
    private static final String COLUMN_START_DATE = "rem_start_date";
    private static final String COLUMN_START_TIME = "rem_start_time";
    private static final String COLUMN_END_DATE = "rem_end_date";
    private static final String COLUMN_END_TIME = "rem_end_time";
    private static final String COLUMN_REM = "rem_rem";
    private static final String COLUMN_LOCATION_NAME = "rem_location_name";
    private static final String COLUMN_LOCATION_ID = "rem_location_id";
    private static final String COLUMN_DESCRIPTION = "rem_description";

    //DATABASE HELPER - CONNECTION BETWEEN JAVA AND SQLITE
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //CREATE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_START_DATE + " DATE, " +
                COLUMN_START_TIME + " TIME, " +
                COLUMN_END_DATE + " DATE, " +
                COLUMN_END_TIME + " TIME, " +
                COLUMN_REM + " TEXT, " +
                COLUMN_LOCATION_NAME + " TEXT, " +
                COLUMN_LOCATION_ID + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }

    //ADD REMINDER TO DATABASE
    public void addReminder(String remName, String remStartDate, String remStartTime, String remEndDate, String remEndTime, String remReminder, String remLocationName, String remLocationId, String remDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, remName);
        contentValues.put(COLUMN_START_DATE, remStartDate);
        contentValues.put(COLUMN_START_TIME, remStartTime);
        contentValues.put(COLUMN_END_DATE, remEndDate);
        contentValues.put(COLUMN_END_TIME, remEndTime);
        contentValues.put(COLUMN_REM, remReminder);
        contentValues.put(COLUMN_LOCATION_NAME, remLocationName);
        contentValues.put(COLUMN_LOCATION_ID, remLocationId);
        contentValues.put(COLUMN_DESCRIPTION, remDescription);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "Error: Reminder could not be added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Reminder Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    //GET REMINDER NAME USING DATES FROM DATABASE
    public ArrayList<String> getRems() {
        SQLiteDatabase db = this.getReadableDatabase();

        //LIST FOR REMINDERS
        ArrayList<String> rems = new ArrayList<>();
        rems.clear();

        //SQL QUERY
        //DATE('NOW') - GETS THE CURRENT DATE
        String query = "SELECT " + COLUMN_NAME + " FROM " + TABLE_NAME + " WHERE " + COLUMN_START_DATE + " >= DATE('now') ORDER BY " + COLUMN_START_DATE + ", " + COLUMN_START_TIME + ";";

        //TOOLS TO WORK THROUGH DATA
        Cursor cursor = db.rawQuery(query, null);
        StringBuffer buffer = new StringBuffer();
        //LOOP THROUGH DATES TO ADD REMS TO ARRAYLIST - REMINDERCALENDAR.CLASS
        if (cursor.moveToFirst()) {
            do {
                String rem = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_NAME));
                buffer.append(rem);
                rems.add(rem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return rems;
    }

    //GET EVENT NAMES FROM DATABASE FOR SELECTED DATE - REMINDERVIEW.JAVA
    public ArrayList<String> getEventNames(String dateSelected) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> events = new ArrayList<>();

        //QUERY TO GET EVENT DETAILS FOR SELECTED DATE
        String query = "SELECT " + COLUMN_NAME + ", " + COLUMN_START_TIME + ", " + COLUMN_END_DATE + ", " + COLUMN_END_TIME + ", " + COLUMN_REM + ", " + COLUMN_LOCATION_NAME + ", " + COLUMN_DESCRIPTION + " FROM " + TABLE_NAME + " WHERE " + COLUMN_START_DATE + " = '" + dateSelected + "' ORDER BY " + COLUMN_START_DATE + ", " + COLUMN_START_TIME + ";";

        //TOOLS TO WORK THROUGH DATA
        Cursor cursor = db.rawQuery(query, null);
        StringBuffer buffer = new StringBuffer();
        //LOOP THROUGH EVENTS AND STORE IN OBJECT
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                //APPEND DETAILS
                StringBuffer name = buffer.append(cursor.getString(1));
                StringBuffer sTime = buffer.append(cursor.getString(3));
                StringBuffer eDate = buffer.append(cursor.getString(4));
                StringBuffer eTime = buffer.append(cursor.getString(5));
                StringBuffer reminder = buffer.append(cursor.getString(6));
                StringBuffer locationN = buffer.append(cursor.getString(7));
                StringBuffer description = buffer.append(cursor.getString(9));

                //CHANGE END DATE FORMAT
                String dateEnd = FormatDate.DateFormatDay(eDate.toString());

                //lOCATION NULL
                String location;
                if (locationN.toString() == "NULL") {
                    location = "No location selected";
                } else {
                    location = locationN.toString();
                }

                //DESCRIPTION EMPTY
                String des;
                if (description.toString().isEmpty()) {
                    des = "No description";
                } else {
                    des = description.toString();
                }

                //STORE RESULTS
                events.add(name.toString() + "\n\tEvent Start: " + sTime.toString() + "\n\tEvent End: " + dateEnd + " - " + eTime.toString() + "\n\tReminder: " + reminder.toString() + "\n\tLocation: " + location + "\n\tDescription: " + des);
            }
        }

        cursor.close();
        db.close();

        return events;
    }

    //FIND EVENT TIMES FOR SELECTED DATE AND EVENT NAME
    public ArrayList<String> getEventTime(String name, String dateSelected) {
        SQLiteDatabase db = this.getReadableDatabase();

        //LIST FOR DATA
        ArrayList<String> eTime = new ArrayList<>();
        eTime.clear();

        //QUERY TO GET EVENT START TIMES FROM DATABASE
        String query = "SELECT " + COLUMN_START_TIME + " FROM " + TABLE_NAME + " WHERE ()" + COLUMN_START_DATE + " = '" + dateSelected + "') AND (" + COLUMN_NAME + " = '" + name + "');";

        //TOOLS TO WORK THROUGH DATA
        Cursor cursor = db.rawQuery(query, null);
        StringBuffer buffer = new StringBuffer();
        String time = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_START_TIME));
        buffer.append(name + "\n\t Event Starts at: " + time);
        eTime.add(name + "\n\t Event Starts at: " + time);

        //CLOSE CONNECTION
        cursor.close();
        db.close();

        //RETURN TIME
        return eTime;
    }

    public ArrayList<String> getLocationName() {
        SQLiteDatabase db = this.getReadableDatabase();

        //LIST FOR DATA
        ArrayList<String> eLocation = new ArrayList<>();
        eLocation.clear();

        //QUERY TO GET EVENT START TIMES FROM DATABASE
        String query = "SELECT " + COLUMN_LOCATION_NAME + " FROM " + TABLE_NAME + ";";

        //TOOLS TO WORK THROUGH DATA
        Cursor cursor = db.rawQuery(query, null);
        StringBuffer buffer = new StringBuffer();
        if (cursor.moveToFirst()){
            do {
                String loc = cursor.getString(cursor.getColumnIndex(databaseHelper.COLUMN_LOCATION_NAME));
                buffer.append(loc);
                eLocation.add(loc);
            } while (cursor.moveToNext());
        }
        //CLOSE CONNECTION
        cursor.close();
        db.close();

        //RETURN LOCATION
        return eLocation;
    }



}

