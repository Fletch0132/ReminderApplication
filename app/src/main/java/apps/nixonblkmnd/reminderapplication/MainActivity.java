package apps.nixonblkmnd.reminderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ListView mainUpcoming;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BUTTONS
        Button btnCalendar = findViewById(R.id.btnCalendar);
        Button btnCreateRem = findViewById(R.id.btnCreateRem);
        Button btnMap = findViewById(R.id.btnMap);
        Button btnNotes = findViewById(R.id.btnNotes);
        Button btnHelp = findViewById(R.id.btnHelp);

        //BUTTONS ONCLICK
        btnCalendar.setOnClickListener(this);
        btnCreateRem.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnNotes.setOnClickListener(this);
        btnHelp.setOnClickListener(this);

        //DISPLAY UPCOMING REMINDERS
        databaseHelper = new DatabaseHelper(this);
        mainUpcoming = (ListView) findViewById(R.id.txtMainUpcoming);
        ViewReminders();
    }


    //DISPLAY UPCOMING REMINDERS METHOD
    public void ViewReminders(){
        try {
            //LIST TO STORE DATES THAT CONTAIN REMINDERS - FROM DATABASEHELPER.JAVA
            ArrayList<String> reminders = databaseHelper.getRems();
            if(reminders.size() <5)
            {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, reminders);
                //FILL LIST VIEW
                mainUpcoming.setAdapter(adapter);
            }
            else {
                ArrayList<String> rems = new ArrayList<>(reminders.subList(0, 5));
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rems);
                //FILL LIST-VIEW
                mainUpcoming.setAdapter(adapter);
            }
        } catch (Exception e){
            Log.e(TAG, "ViewRemindersMain: Error Here");
        }
    }

    //NAVIGATE ONCLICK
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCalendar:
                navCalendar();
                break;
            case R.id.btnCreateRem:
                navCreateRem();
                break;
            case R.id.btnMap:
                navMap();
                break;
            case R.id.btnNotes:
                navNotes();
                break;
            case R.id.btnHelp:
                navHelp();
                break;
        }
    }

    //NAVIGATE TO CALENDAR
    private void navCalendar(){
        Intent intent = new Intent(this, ReminderCalendar.class);
        startActivity(intent);
    }

    //NAVIGATE TO CREATE REMINDER
    private void navCreateRem(){
        Intent intent = new Intent(this, ReminderCreate.class);
        startActivity(intent);
    }

    //NAVIGATE TO MAP
    private void navMap(){
        Intent intent = new Intent(this, ReminderMap.class);
        startActivity(intent);
    }

    //NAVIGATE TO NOTES
    private void navNotes(){
        Intent intent = new Intent(this, ReminderNotes.class);
        startActivity(intent);
    }

    //NAVIGATE TO SELF-HELP
    private void navHelp(){
        Intent intent = new Intent(this, ReminderHelp.class);
        startActivity(intent);
    }
}