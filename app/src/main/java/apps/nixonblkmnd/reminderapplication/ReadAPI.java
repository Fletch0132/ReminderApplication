package apps.nixonblkmnd.reminderapplication;

/*import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//READ API KEY FROM FILE AND SAVE TO STRING FOR APPLICATION TO ACCESS
public class ReadAPI {

    public String readFromFile(Context context){

        //STRING FOR STORING API KEY
        String key = "";

        try{
            InputStream inputStream = context.openFileInput("cd ../../APIKEY_GOOGLE.txt");

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null){
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                key = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Retrieve API-KEY", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Retrieve API_KEY", "Cannot read file: " + e.toString());
        }

        return key;
    }

}*/
