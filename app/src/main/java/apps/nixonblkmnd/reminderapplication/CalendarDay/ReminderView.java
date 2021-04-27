package apps.nixonblkmnd.reminderapplication.CalendarDay;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import apps.nixonblkmnd.reminderapplication.Formats.FormatDate;
import apps.nixonblkmnd.reminderapplication.R;
import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;

import static android.content.ContentValues.TAG;

public class ReminderView extends AppCompatActivity {

    //LISTVIEW
    ListView remViewUpcoming;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_view);

        //VARIABLES - DATABASE, LISTVIEW
        databaseHelper = new DatabaseHelper(this);
        remViewUpcoming = findViewById(R.id.txtRemUpcoming);

        DisplayEvents();
    }

    public void DisplayEvents(){
        //RETRIEVE DATE FROM CALENDAR DATE SELECTED
        String dateSelected = getIntent().getStringExtra("dateCL");

        //CHANGE DATE FORMAT FOR DATABASE
        dateSelected = FormatDate.DateFormatYear(dateSelected);

        try {
            //STORE OBJECT WITHIN AN ARRAY
            ArrayList<String> event = databaseHelper.getEventNames(dateSelected);

            //DISPLAY EVENT DATA
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, event);
            remViewUpcoming.setAdapter(adapter);
        }catch (Exception e){
            Log.e(TAG, "Selected Day error getting events");
        }
    }
}
