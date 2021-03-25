package apps.nixonblkmnd.reminderapplication;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import apps.nixonblkmnd.reminderapplication.database.DatabaseHelper;
import apps.nixonblkmnd.reminderapplication.objects.ObjectCalendar;

public class ReminderCalendar extends AppCompatActivity implements View.OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_calendar);

        //DATABASE
        DatabaseHelper databaseHelper = new DatabaseHelper(ReminderCalendar.this);
        List<ObjectCalendar> objCal = databaseHelper.objCal();



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