package apps.nixonblkmnd.reminderapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

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
                COLUMN_START_DATE + " TEXT, " +
                COLUMN_START_TIME + " TEXT, " +
                COLUMN_END_DATE + " TEXT, " +
                COLUMN_END_TIME + " TEXT, " +
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
    public void addReminder(String remName, String remStartDate, String remStartTime, String remEndDate, String remEndTime, String remReminder, String remLocationName, String remLocationId, String remDescription){
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
        if(result == -1){
            Toast.makeText(context, "Error: Reminder could not be added", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Reminder Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}
