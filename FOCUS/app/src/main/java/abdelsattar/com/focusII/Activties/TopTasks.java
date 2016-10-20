package abdelsattar.com.focusII.Activties;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import abdelsattar.com.focusII.Constants;
import abdelsattar.com.focusII.R;

public class TopTasks extends AppCompatActivity implements View.OnClickListener {

   private ImageView editOne, editTwo, editThree;
   private TextView top1, top2, top3;
   private String AddedString;
   private String top1Str, top2Str, top3Str;
   private SharedPreferences sharedPreferences;
   private List<String> topTasksStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tasks);
        initializeScreen();
        editOne.setOnClickListener(this);
        editTwo.setOnClickListener(this);
        editThree.setOnClickListener(this);

        getDataFromSharedPreferences();

    }

    private void getDataFromSharedPreferences() {
        sharedPreferences = getSharedPreferences(Constants.KEY_SharedPreference, Context.MODE_PRIVATE);

        top1Str = sharedPreferences.getString(Constants.KEY_Pref_one, null);
        top2Str = sharedPreferences.getString(Constants.KEY_Pref_two, null);
        top3Str = sharedPreferences.getString(Constants.KEY_Pref_three, null);

        if (top1Str != null) {
            top1.setText(top1Str);
            topTasksStr.add(top1Str);
        } else {
            topTasksStr.add("Top 1");
        }
        if (top2Str != null) {
            top2.setText(top2Str);
            topTasksStr.add(top2Str);
        } else {
            topTasksStr.add("Top 2");
        }
        if (top3Str != null) {
            top3.setText(top3Str);
            topTasksStr.add(top3Str);
        } else {
            topTasksStr.add("Top 3");
        }
    }

    public void initializeScreen() {
        topTasksStr = new ArrayList<>();
        top1 = (TextView) findViewById(R.id.Top3_one);
        top2 = (TextView) findViewById(R.id.Top3_two);
        top3 = (TextView) findViewById(R.id.Top3_three);

        editOne = (ImageView) findViewById(R.id.EditOne);
        editTwo = (ImageView) findViewById(R.id.EditTwo);
        editThree = (ImageView) findViewById(R.id.EditThree);
        setupGoogleAds();
    }

    private void setupGoogleAds() {

        AdView mAdView;
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(getApplicationContext(), getString(R.string.app_id_example));

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.TopTasks_adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                //  .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View view) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (view.getId()) {
            case R.id.EditOne: {
                setupAddTaskDialog(1);
                break;
            }
            case R.id.EditTwo: {
                setupAddTaskDialog(2);
                break;
            }
            case R.id.EditThree: {
                setupAddTaskDialog(3);
                break;
            }
        }
        editor.commit();
    }


    private void setupAddTaskDialog(final int num) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_edit_text, null);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this, R.style.InvitationDialog);

        alertDialogBuilder.setTitle("Edit Top " + num);
        alertDialogBuilder.setView(promptsView);

        final EditText taskDialog = (EditText) promptsView
                .findViewById(R.id.DialogET);

        taskDialog.setText(topTasksStr.get(num - 1));

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (taskDialog.getText().toString().trim().equalsIgnoreCase("")) {
                                    taskDialog.setError("This field can not be blank");
                                } else {
                                    AddedString = taskDialog.getText().toString();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    if (num == 1) {
                                        editor.putString(Constants.KEY_Pref_one, AddedString);
                                        top1.setText(AddedString);
                                    } else if (num == 2) {

                                        editor.putString(Constants.KEY_Pref_two, AddedString);
                                        top2.setText(AddedString);

                                    } else if (num == 3) {
                                        editor.putString(Constants.KEY_Pref_three, AddedString);
                                        top3.setText(AddedString);

                                    }
                                    editor.commit();
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
