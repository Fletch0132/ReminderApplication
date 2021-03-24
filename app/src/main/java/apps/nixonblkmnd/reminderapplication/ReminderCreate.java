package apps.nixonblkmnd.reminderapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

import static android.content.ContentValues.TAG;


public class ReminderCreate extends AppCompatActivity {

    //DECLARE
    EditText txtRemName;
    TextView txtRemStartDate;
    TextView txtRemStartTime;
    TextView txtRemEndDate;
    TextView txtRemEndTime;
    Spinner txtRemRem;
    EditText txtRemDescription;
    Button btnAddReminder;
    String tempRem;



    //INITIALIZE REMINDEROBJECT.CLASS
    ReminderObject remObj = new ReminderObject();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_create);


        //EDIT TEXT BOXES
        txtRemName = (EditText) findViewById(R.id.txtRemName);
        txtRemStartDate = (TextView) findViewById(R.id.txtRemStartDate);
        txtRemStartTime = (TextView) findViewById(R.id.txtRemStartTime);
        txtRemEndDate = (TextView) findViewById(R.id.txtRemEndDate);
        txtRemEndTime = (TextView) findViewById(R.id.txtRemEndTime);
        txtRemRem = (Spinner) findViewById(R.id.txtRemRem);
        txtRemDescription = (EditText) findViewById(R.id.txtRemDescription);



        //LIST ARRAY FOR FILLING REMINDER TIME
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SpinnerRemRem, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtRemRem.setAdapter(adapter);


        //BUTTON
        btnAddReminder = (Button) findViewById(R.id.btnAddReminder);


        //START DATE
        txtRemStartDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                handleStartDate();
            }
        });
        //START TIME
        txtRemStartTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                handleStartTime();
            }
        });
        //END DATE
        txtRemEndDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                handleEndDate();
            }
        });
        //END TIME
        txtRemEndTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                handleEndTime();
            }
        });
        //LOCATION
        handleLocation();


        //BUTTON ONCLICK
        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtRemName.getText().toString().isEmpty()) {
                    txtRemName.requestFocus();
                    txtRemName.setError("Reminder requires a name. Please try again.");
                }
                else {
                    //STORE REMINDER NAME
                    remObj.setReminderName(txtRemName.getText().toString());
                    remObj.setRemRem(txtRemRem.getSelectedItem().toString());

                    //DISPLAY SUCCESS
                    Toast.makeText(ReminderCreate.this, remObj.getReminderName() + " reminder added!", Toast.LENGTH_SHORT).show();
                    //NAVIGATE TO MAIN PAGE
                    navMain();
                }

            }
        });

        }


    //NAVIGATE TO MAIN PAGE
    private void navMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    //HANDLES THE START DATE INPUT
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleStartDate() {
        //INITIALIZE CALENDAR
        Calendar calendar = Calendar.getInstance();

        //SET UP CURRENT YEAR, MONTH, DATE USING CALENDAR
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        //PICK DATE
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String startDateString = (dayOfMonth + "/" + month + "/" + year);
                txtRemStartDate.setText(startDateString);
                remObj.setReminderStartDate(startDateString);
            }
        }, year, month, date);

        //DISPLAY
        datePickerDialog.show();
    }

    //HANDLES THE START TIME INPUT
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleStartTime() {
        //INITIALIZE CALENDAR
        Calendar calendar = Calendar.getInstance();
        //SET TIME VARIABLES
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        //PICK TIME
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String startTimeString = (hourOfDay + ":" + minute);
                txtRemStartTime.setText(startTimeString);
                remObj.setReminderStartTime(startTimeString);
            }
        }, hour, minute, true);

        //DISPLAY
        timePickerDialog.show();
    }

    //HANDLES THE END DATE INPUT
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleEndDate(){
        //INITIALIZE CALENDAR
        Calendar calendar = Calendar.getInstance();

        //SET UP CURRENT YEAR, MONTH, DATE USING CALENDAR
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);

        //PICK DATE
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String endDateString = (dayOfMonth + "/" + month + "/" + year);
                txtRemEndDate.setText(endDateString);
                remObj.setReminderEndDate(endDateString);
            }
        }, year, month, date);

        //DISPLAY
        datePickerDialog.show();
    }

    //HANDLES THE END TIME INPUT
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void handleEndTime(){
        //INITIALIZE CALENDAR
        Calendar calendar = Calendar.getInstance();
        //SET TIME VARIABLES
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        //PICK TIME
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String endTimeString = (hourOfDay + ":" + minute);
                txtRemEndTime.setText(endTimeString);
                remObj.setReminderEndTime(endTimeString);
            }
        }, hour, minute, true);

        //DISPLAY
        timePickerDialog.show();
    }





    //HANDLES LOCATION AUTOCOMPLETE AND SELECTION
    private void handleLocation() {

        // INITIALIZE PLACES
        Places.initialize(getApplicationContext(), BuildConfig.GMP_KEY);
        //CHECK INITIALIZE AND PUSH FOR ONE
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), BuildConfig.GMP_KEY);
        }
        // CREATE A NEW PLACESCLIENT
        PlacesClient placesClient = Places.createClient(this);
        //INITIALIZE AUTOCOMPLETESUPPORTFRAGMENT
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_Fragment);
        //SPECIFY PLACE FOR USER INPUT
        //autocompleteSupportFragment.setTypeFilter(TypeFilter.ADDRESS);
        //LOCATION BIAS TO HELP RESULTS
        autocompleteSupportFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(54.922195, -5.184964),
                new LatLng(57.695811, -2.0116)));
        autocompleteSupportFragment.setCountries("UK");
        //SPECIFY PLACE DATA TO RETURN
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
        //RETRIEVE NAME AND ID OF PLACE
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                //GET INFO ON SELECTED PLACE
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                remObj.setReminderLocationName(place.getName());
                remObj.setReminderLocationId(place.getId());
            }

            @Override
            public void onError(Status status) {
                //ERROR HANDLING
                Log.i(TAG, "An Error Occurred: " + status);
            }
        });
    }
}