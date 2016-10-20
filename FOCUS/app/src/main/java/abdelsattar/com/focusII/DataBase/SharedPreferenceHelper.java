package abdelsattar.com.focusII.DataBase;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import abdelsattar.com.focusII.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Abd El-Sattar on 8/9/2016.
 */

public class SharedPreferenceHelper {

    public void savePressedState(boolean[] isPressed, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.KEY_PREF_SAGDA, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();

        String jsonText = gson.toJson(isPressed);
        editor.putString(Constants.KEY_PREF_SAGDA_STATUS, jsonText);
        editor.apply();

    }

    public boolean[] retrieveSagdaState(Context context) {
        boolean isPressedSagda[] = new boolean[5];

        SharedPreferences prefs = context.getSharedPreferences(Constants.KEY_PREF_SAGDA, MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonText = prefs.getString(Constants.KEY_PREF_SAGDA_STATUS, null);
        if (jsonText != null)
            isPressedSagda = gson.fromJson(jsonText, boolean[].class);
        else {
            isPressedSagda = new boolean[5];
            for (int i = 0; i < isPressedSagda.length; i++) {
                isPressedSagda[i] = false;
            }
        }
        return isPressedSagda;
    }
}
