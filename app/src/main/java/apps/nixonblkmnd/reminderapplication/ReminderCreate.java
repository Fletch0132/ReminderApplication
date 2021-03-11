package apps.nixonblkmnd.reminderapplication;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
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
    EditText txtRemLocation;
    EditText txtRemDescription;
    Button btnAddReminder;

    //DEFINE AWESOMEVALIDATION OBJECT
    //AwesomeValidation awesomeValidation;

    //VARIABLES



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
        txtRemDescription = (EditText) findViewById(R.id.txtRemDescription);

        //VARIABLES
        String remName, remDescription;


        //BUTTON
        btnAddReminder = (Button) findViewById(R.id.btnAddReminder);


        //INITIALIZE
        //awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        //START DATE
        txtRemStartDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                handleStartDate();
            }
        });

        //START TIME
        handleStartTime();

        //END DATE
        handleEndDate();

        //END TIME
        handleEndTime();

        //LOCATION
        handleLocation();


        //BUTTON ONCLICK
        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btnAddReminder) {
                    Toast.makeText(getApplicationContext(),"Works", Toast.LENGTH_LONG).show();
                }
            }
        });

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
            }
        }, year, month, date);

        //DISPLAY
        datePickerDialog.show();
    }

    //HANDLES THE START TIME INPUT
    private void handleStartTime() {

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
            }
        }, year, month, date);

        //DISPLAY
        datePickerDialog.show();
    }

    //HANDLES THE END TIME INPUT
    private void handleEndTime(){

    }

    //HANDLES LOCATION AUTOCOMPLETE AND SELECTION
    private void handleLocation() {
        // INITIALIZE PLACES
        Places.initialize(getApplicationContext(), getString(R.string.api_key));
        //CHECK INITIALIZE AND PUSH FOR ONE
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.api_key));
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
            }

            @Override
            public void onError(Status status) {
                //ERROR HANDLING
                Log.i(TAG, "An Error Occurred: " + status);
            }
        });
    }


}