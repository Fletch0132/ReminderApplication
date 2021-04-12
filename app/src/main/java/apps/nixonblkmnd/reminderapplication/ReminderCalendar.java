package apps.nixonblkmnd.reminderapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;

import static android.content.ContentValues.TAG;

public class ReminderCalendar extends AppCompatActivity implements View.OnClickListener {

    ListView calUpcoming;
    DatabaseHelper databaseHelper;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_calendar);

        //DATABASE
        databaseHelper = new DatabaseHelper(this);
        calUpcoming = (ListView) findViewById(R.id.txtCalUpcoming);

        //CALLS METHOD TO DISPLAY UPCOMING REMINDERS
        ViewReminders();

        //BUTTON
        Button btnCreateRemCal = findViewById(R.id.btnCreateRemCal);

        //BUTTON ONCLICK
        btnCreateRemCal.setOnClickListener(this);
    }


    //VIEW REMINDERS ON CALENDAR
    public void ViewReminders(){
        try {
            //LIST TO STORE DATES THAT CONTAIN REMINDERS - FROM DATABASEHELPER.JAVA
            ArrayList<String> reminders = databaseHelper.getRems();
            if(reminders.size() <3)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reminders);
                //FILL LIST VIEW
                calUpcoming.setAdapter(adapter);
            }
            else {
                ArrayList<String> rems = new ArrayList<>(reminders.subList(0, 3));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rems);
                //FILL LIST-VIEW
                calUpcoming.setAdapter(adapter);
            }
        } catch (Exception e){
            Log.e(TAG, "ViewReminders: Error Here");
        }
    }





    //NAVIGATE ONCLICK
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ReminderCreate.class);
        startActivity(intent);
    }
}