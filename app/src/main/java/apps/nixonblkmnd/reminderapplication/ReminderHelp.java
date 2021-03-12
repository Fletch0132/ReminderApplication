package apps.nixonblkmnd.reminderapplication;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ReminderHelp extends AppCompatActivity {

    //DECLARE
    EditText txtHelpInfo1;
    EditText txtHelpInfo2;
    EditText txtHelpInfo3;
    EditText txtHelpInfo4;
    EditText txtHelpInfo5;

    //POSSIBLY ADDED TO DB SO MORE CAN BE ADDED?
    //COPY TEXT WHEN CLICKED - COPY NUMBER - CALL ORG

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_help);

        //EDIT TEXT BOXES
        txtHelpInfo1 = (EditText) findViewById(R.id.txtHelpInfo1);
        txtHelpInfo2 = (EditText) findViewById(R.id.txtHelpInfo2);
        txtHelpInfo3 = (EditText) findViewById(R.id.txtHelpInfo3);
        txtHelpInfo4 = (EditText) findViewById(R.id.txtHelpInfo4);
        txtHelpInfo5 = (EditText) findViewById(R.id.txtHelpInfo5);

        //CALL METHODS TO FILL EDIT TEXT BOXES
        //ALL INFO FROM NHS - CHECK COPYRIGHT
        FillInfo1();
        FillInfo2();
        FillInfo3();
        FillInfo4();
        FillInfo5();
    }

    private void FillInfo1() {
        String helpOrg = ("Organisation: Anxiety UK");
        String helpDes = ("Description: Charity providing support if you have been diagnosed with an anxiety condition.");
        String helpCon = ("Contact: \n03444 775 774 \nwww.anxietyuk.org.uk");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        txtHelpInfo1.setText(helpInfo);
    }

    private void FillInfo2() {
        String helpOrg = ("Organisation: CALM");
        String helpDes = ("Description: CALM is the Campaign Against Living Miserably. A charity providing a mental health helpline and webchat.");
        String helpCon = ("Contact: \n0800 58 58 58 \nwww.thecalmzone.net");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        txtHelpInfo2.setText(helpInfo);
    }

    private void FillInfo3() {
        String helpOrg = ("Organisation: MIND");
        String helpDes = ("Description: Promotes the views and needs of people with mental health problems.");
        String helpCon = ("Contact: \n0300 123 3393 \nwww.mind.org.uk");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        txtHelpInfo3.setText(helpInfo);
    }

    private void FillInfo4() {
        String helpOrg = ("Organisation: Samaritans");
        String helpDes = ("Description: Confidential support for people experiencing feelings of distress or despair.");
        String helpCon = ("Contact: \n116 123 \nwww.samaritans.org.uk");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        txtHelpInfo4.setText(helpInfo);
    }

    private void FillInfo5() {
        String helpOrg = ("Organisation: PAPYRUS");
        String helpDes = ("Description: Young Suicide prevention society.");
        String helpCon = ("Contact: \n0800 068 4141 \nwww.papyrus-uk.org");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        txtHelpInfo5.setText(helpInfo);
    }
}