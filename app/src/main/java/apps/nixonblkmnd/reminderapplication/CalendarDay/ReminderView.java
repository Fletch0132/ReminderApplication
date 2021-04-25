package apps.nixonblkmnd.reminderapplication.CalendarDay;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import apps.nixonblkmnd.reminderapplication.Formats.FormatDate;
import apps.nixonblkmnd.reminderapplication.R;
import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;

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

        ArrayList<String> events = Events();

        PopulateEvents(events);
    }

    public ArrayList<String> Events(){
        //RETRIEVE DATE FROM CALENDAR DATE SELECTED
        String dateSelected = getIntent().getStringExtra("dateCL");

        //CHANGE DATE FORMAT FOR DATABASE
        dateSelected = FormatDate.DateFormatYear(dateSelected);

        //GET EVENTS/REMINDERS FROM DATABASE FOR THE DATE SELECTED
        ArrayList<String> eNames = databaseHelper.getEventNames(dateSelected);

        //ARRAYLIST FOR EVENT DATA
        ArrayList<String> eventData = new ArrayList<>();

        //FILTER THROUGH EVENT NAMES TO GET DATA ON EVENTS
        for (int i=0; i<eNames.size();i++){
            String name = eNames.get(i);

            //FIND TIME FOR EVENT IN DATABASE
            String eTime = databaseHelper.getEventTime(name, dateSelected);

            //ADD EVENT NAME AND TIME TO ARRAYLIST
            eventData.add(name + "\n Event Start Time: " + eTime);
        }
        return eventData;
    }

    //METHOD FILLS LIST VIEW
    public void PopulateEvents(ArrayList<String> events){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, events);
        //FILL LIST VIEW
        remViewUpcoming.setAdapter(adapter);
    }
}