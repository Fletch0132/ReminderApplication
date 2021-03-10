package apps.nixonblkmnd.reminderapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

import static android.content.ContentValues.TAG;


public class ReminderCreate extends AppCompatActivity implements View.OnClickListener {

    //DECLARE
    EditText txtRemName, txtRemStartDate, txtRemStartTime, txtRemEndDate, txtRemEndTime, txtRemLocation, txtRemDescription;
    Button btnAddReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_create);

        //EDIT TEXT BOXES
        txtRemName = (EditText) findViewById(R.id.txtRemName);
        txtRemStartDate = (EditText) findViewById(R.id.txtRemStartDate);
        txtRemStartTime = (EditText) findViewById(R.id.txtRemStartTime);
        txtRemEndDate = (EditText) findViewById(R.id.txtRemEndDate);
        txtRemEndTime = (EditText) findViewById(R.id.txtRemEndTime);
        txtRemDescription = (EditText) findViewById(R.id.txtRemDescription);

        //BUTTON
        btnAddReminder = (Button) findViewById(R.id.btnAddReminder);

        //DEFINE AWESOMEVALIDATION OBJECT
        AwesomeValidation awesomeValidation;
        //INITIALIZE
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        //****LOCATION****
        // INITIALIZE PLACES
        Places.initialize(getApplicationContext(), "AIzaSyAfNcD97aOGLWgZEP4vhaRPnyDN2eq3h8c");
        // CREATE A NEW PLACESCLIENT
        PlacesClient placesClient = Places.createClient(this);
        //INITIALIZE AUTOCOMPLETESUPPORTFRAGMENT
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment)getSupportFragmentManager().findFragmentById(R.id.autocomplete_Fragment);
        //SPECIFY PLACE FOR USER INPUT
        autocompleteSupportFragment.setTypeFilter(TypeFilter.ADDRESS);
        //LOCATION BIAS TO HELP RESULTS
        autocompleteSupportFragment.setLocationBias(RectangularBounds.newInstance(
                new LatLng(54.922195, -5.184964),
                new LatLng(57.695811, -2.0116)));
        autocompleteSupportFragment.setCountries("UK");
        //SPECIFY PLACE DATA TO RETURN
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

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


    //ONCLICK
    @Override
    public void onClick(View v) {
        if (v == btnAddReminder){
            submitReminder();
        }


    }
}