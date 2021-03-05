package apps.nixonblkmnd.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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