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
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static android.content.ContentValues.TAG;


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

        // Initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyAfNcD97aOGLWgZEP4vhaRPnyDN2eq3h8c");
        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);



        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NotNull Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }


            @Override
            public void onError(@NotNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
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


    @Override
    public void onClick(View v) {
        //SET VARIABLES


    }
}