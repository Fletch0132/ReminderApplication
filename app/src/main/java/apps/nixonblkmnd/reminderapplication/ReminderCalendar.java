package apps.nixonblkmnd.reminderapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;

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
        //LIST TO STORE DATES THAT CONTAIN REMINDERS - FROM DATABASEHELPER.JAVA
        ArrayList<String> remDates = databaseHelper.getRemDates();
        //LIST THAT TAKES FIRST THREE REMINDERS IN REMDATES
        ArrayList<String> rD = new ArrayList<>(remDates.subList(0,3));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rD);
        //FILL LIST-VIEW
        calUpcoming.setAdapter(adapter);
    }


    //CHANGE DATE FORMAT - YYYY-MM-DD
    public static String DateFormatYear(Context context, String dateIn){
        String dateOut = " ";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return dateOut;

    }


    //CHANGE DATE FORMAT - DD-MM-YYYY
    public static String DateFormatDay(Context context, String dateIn){
        String dateOut = " ";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return dateOut;

    }


    //NAVIGATE ONCLICK
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ReminderCreate.class);
        startActivity(intent);
    }
}