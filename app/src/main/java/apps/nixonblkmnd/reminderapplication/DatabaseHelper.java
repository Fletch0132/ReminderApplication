package apps.nixonblkmnd.reminderapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "ReminderApplication.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "reminders";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "rem_name";
    public static final String COLUMN_START_DATE = "rem_start_date";
    public static final String COLUMN_START_TIME = "rem_start_time";
    public static final String COLUMN_END_DATE = "rem_end_date";
    public static final String COLUMN_END_TIME = "rem_end_time";
    public static final String COLUMN_LOCATION = "rem_location";
    public static final String COLUMN_DESCRIPTION = "rem_description";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
