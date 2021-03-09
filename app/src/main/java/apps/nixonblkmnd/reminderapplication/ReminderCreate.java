package apps.nixonblkmnd.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReminderCreate extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_create);

        //EDIT TEXT BOXES
        EditText txtRemName = findViewById(R.id.txtRemName);
        EditText txtRemStartDate = findViewById(R.id.txtRemStartDate);
        EditText txtRemStartTime = findViewById(R.id.txtRemStartTime);
        EditText txtRemEndDate = findViewById(R.id.txtRemEndDate);
        EditText txtRemEndTime = findViewById(R.id.txtRemEndTime);
        EditText txtRemLocation = findViewById(R.id.txtRemLocation);
        EditText txtRemDescription = findViewById(R.id.txtRemDescription);
        //BUTTON
        Button btnAddReminder = findViewById(R.id.btnAddReminder);

        //VALIDATE INPUT AND STORE TO DISPLAY REMINDER AND NOTIFY USER


        //BUTTON ONCLICK
        btnAddReminder.setOnClickListener(this);
    }


    //VALIDATES REMINDER AND STORES
    public void submitReminder(){

    }


    @Override
    public void onClick(View v) {
        //SET VARIABLES


    }
}