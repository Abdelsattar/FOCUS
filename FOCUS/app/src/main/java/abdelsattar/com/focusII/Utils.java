package abdelsattar.com.focusII;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Abd El-Sattar on 8/9/2016.
 */

public class Utils {

    public void savePressedState(boolean[] isPressed, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.KEY_PREF_SAGDA, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();

        String jsonText = gson.toJson(isPressed);
        editor.putString(Constants.KEY_PREF_SAGDA_STATUS, jsonText);
        editor.apply();

    }

    public boolean[] retrieveSagdaState(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.KEY_PREF_SAGDA, MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonText = prefs.getString(Constants.KEY_PREF_SAGDA_STATUS, null);
        return gson.fromJson(jsonText, boolean[].class);
    }

}
