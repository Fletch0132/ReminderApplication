package apps.nixonblkmnd.reminderapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReminderHelp extends AppCompatActivity {

    //DECLARE
    Button btnHelpInfo1;
    Button btnHelpInfo2;
    Button btnHelpInfo3;
    Button btnHelpInfo4;
    Button btnHelpInfo5;

    //POSSIBLY ADDED TO DB SO MORE CAN BE ADDED?
    //COPY TEXT WHEN CLICKED - COPY NUMBER - CALL ORG

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_help);

        //EDIT TEXT BOXES
        btnHelpInfo1 = (Button) findViewById(R.id.btnHelpInfo1);
        btnHelpInfo2 = (Button) findViewById(R.id.btnHelpInfo2);
        btnHelpInfo3 = (Button) findViewById(R.id.btnHelpInfo3);
        btnHelpInfo4 = (Button) findViewById(R.id.btnHelpInfo4);
        btnHelpInfo5 = (Button) findViewById(R.id.btnHelpInfo5);

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
        btnHelpInfo1.setText(helpInfo);
    }

    private void FillInfo2() {
        String helpOrg = ("Organisation: CALM");
        String helpDes = ("Description: CALM is the Campaign Against Living Miserably. A charity providing a mental health helpline and webchat.");
        String helpCon = ("Contact: \n0800 58 58 58 \nwww.thecalmzone.net");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        btnHelpInfo2.setText(helpInfo);
    }

    private void FillInfo3() {
        String helpOrg = ("Organisation: MIND");
        String helpDes = ("Description: Promotes the views and needs of people with mental health problems.");
        String helpCon = ("Contact: \n0300 123 3393 \nwww.mind.org.uk");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        btnHelpInfo3.setText(helpInfo);
    }

    private void FillInfo4() {
        String helpOrg = ("Organisation: Samaritans");
        String helpDes = ("Description: Confidential support for people experiencing feelings of distress or despair.");
        String helpCon = ("Contact: \n116 123 \nwww.samaritans.org.uk");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        btnHelpInfo4.setText(helpInfo);
    }

    private void FillInfo5() {
        String helpOrg = ("Organisation: PAPYRUS");
        String helpDes = ("Description: Young Suicide prevention society.");
        String helpCon = ("Contact: \n0800 068 4141 \nwww.papyrus-uk.org");
        String helpInfo = (helpOrg + "\n" + helpDes + "\n" + helpCon);
        btnHelpInfo5.setText(helpInfo);
    }
}