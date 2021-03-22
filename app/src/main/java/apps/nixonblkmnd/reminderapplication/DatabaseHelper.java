package apps.nixonblkmnd.reminderapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

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
    private static final String COLUMN_LOCATION = "rem_location";
    private static final String COLUMN_DESCRIPTION = "rem_description";

    //DATABASE HELPER - CONNECTION BETWEEN JAVA AND SQLITE
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_START_DATE + " TEXT, " +
                COLUMN_START_TIME + " TEXT, " +
                COLUMN_END_DATE + " TEXT, " +
                COLUMN_END_TIME + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }
}
