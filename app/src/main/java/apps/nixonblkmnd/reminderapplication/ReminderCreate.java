package apps.nixonblkmnd.reminderapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;


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
            //location here
        EditText txtRemDescription = findViewById(R.id.txtRemDescription);

        //BUTTON
        Button btnAddReminder = findViewById(R.id.btnAddReminder);

        //DEFINE AWESOMEVALIDATION OBJECT
        AwesomeValidation awesomeValidation;
        //INITIALIZE
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);



        //VALIDATE INPUT AND STORE TO DISPLAY REMINDER AND NOTIFY USER
        awesomeValidation.addValidation(this, R.id.txtRemName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.remNameError);
        awesomeValidation.addValidation(this, R.id.txtRemStartDate, "^(?=\\s*\\S).*$", R.string.remStartDateError);
        awesomeValidation.addValidation(this, R.id.txtRemStartTime, "^(?=\\s*\\S).*$", R.string.remStartTimeError);
        awesomeValidation.addValidation(this, R.id.txtRemEndDate, "^(?=\\s*\\S).*$", R.string.remEndDateError);
        awesomeValidation.addValidation(this, R.id.txtRemEndTime, "^(?=\\s*\\S).*$", R.string.remEndTimeError);



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