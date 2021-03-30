package apps.nixonblkmnd.reminderapplication;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;

public class ReminderCalendar extends AppCompatActivity implements View.OnClickListener {

    ListView txtCalUpcoming;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_calendar);

        txtCalUpcoming = (ListView) findViewById(R.id.txtCalUpcoming);

        //DATABASE
        DatabaseHelper databaseHelper = new DatabaseHelper(ReminderCalendar.this);


        //BUTTON
        Button btnCreateRemCal = findViewById(R.id.btnCreateRemCal);

        //BUTTON ONCLICK
        btnCreateRemCal.setOnClickListener(this);
    }




    //NAVIGATE ONCLICK
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ReminderCreate.class);
        startActivity(intent);
    }
}