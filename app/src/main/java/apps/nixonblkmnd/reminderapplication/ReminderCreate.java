package apps.nixonblkmnd.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReminderCreate extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_create);

        //BUTTON
        Button btnAddReminder = findViewById(R.id.btnAddReminder);

        //BUTTON ONCLICK
        btnAddReminder.setOnClickListener(this);
    }


    //VALIDATE INPUT AND STORE TO DISPLAY REMINDER AND NOTIFY USER
    //THEN RETURN TO MAIN-PAGE/CALENDAR
    @Override
    public void onClick(View v) {
        //SET VARIABLES


    }
}