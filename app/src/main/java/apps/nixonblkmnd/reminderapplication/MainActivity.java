package apps.nixonblkmnd.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Buttons
    private Button btnCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalendar = (Button) findViewById(R.id.btnCalendar);
        btnCalendar.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                navReminderCalendar();
            }
        });
    }

    public void navReminderCalendar() {
        Intent intent = new Intent(this, ReminderCalendar.class);
        startActivity(intent);
    }
}