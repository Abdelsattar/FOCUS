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

public class Contact extends AppCompatActivity implements View.OnClickListener {

    ImageView editOne, editTwo, editThree;
    TextView top1, top2, top3;
    String AddedString;
    SharedPreferences sharedPreferences;
    List<String> topPersonsStr;

    String top1Str, top2Str, top3Str;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initializeScreen();
        getDataFromSharedPreferences();

    }

    private void getDataFromSharedPreferences() {
        sharedPreferences = getSharedPreferences(Constants.KEY_SharedPreference, Context.MODE_PRIVATE);

        top1Str = sharedPreferences.getString(Constants.KEY_Pref_1st_person, null);
        top2Str = sharedPreferences.getString(Constants.KEY_Pref_2nd_person, null);
        top3Str = sharedPreferences.getString(Constants.KEY_Pref_3rd_person, null);

        if (top1Str != null) {
            top1.setText(top1Str);
            topPersonsStr.add(top1Str);
        } else {
            topPersonsStr.add("1st Person");
        }
        if (top2Str != null) {
            top2.setText(top2Str);
            topPersonsStr.add(top2Str);
        } else {
            topPersonsStr.add("2nd Person");
        }
        if (top3Str != null) {
            top3.setText(top3Str);
            topPersonsStr.add(top3Str);
        } else {
            topPersonsStr.add("3rd Person");
        }
    }

    private void initializeScreen() {
        topPersonsStr = new ArrayList<>();
        top1 = (TextView) findViewById(R.id._1stPerson);
        top2 = (TextView) findViewById(R.id._2ndPerson);
        top3 = (TextView) findViewById(R.id._3rdPerson);

        editOne = (ImageView) findViewById(R.id.EditPersonOne);
        editTwo = (ImageView) findViewById(R.id.EditPersonTwo);
        editThree = (ImageView) findViewById(R.id.EditPersonThree);


        editOne.setOnClickListener(this);
        editTwo.setOnClickListener(this);
        editThree.setOnClickListener(this);
        setupGoogleAds();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.EditPersonOne: {
                setupAddTaskDialog(1);
                break;
            }
            case R.id.EditPersonTwo: {
                setupAddTaskDialog(2);
                break;
            }
            case R.id.EditPersonThree: {
                setupAddTaskDialog(3);
                break;
            }
        }

    }

    private void setupAddTaskDialog(final int num) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_edit_text, null);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this, R.style.InvitationDialog);

        alertDialogBuilder.setTitle("Edit Contact person " + num);
        alertDialogBuilder.setView(promptsView);

        final EditText taskDialog = (EditText) promptsView
                .findViewById(R.id.DialogET);

        taskDialog.setText(topPersonsStr.get(num - 1));

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

                                        editor.putString(Constants.KEY_Pref_1st_person, AddedString);
                                        top1.setText(AddedString);
                                    } else if (num == 2) {

                                        editor.putString(Constants.KEY_Pref_2nd_person, AddedString);
                                        top2.setText(AddedString);

                                    } else if (num == 3) {
                                        editor.putString(Constants.KEY_Pref_3rd_person, AddedString);
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

    private void setupGoogleAds() {

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(getApplicationContext(), getString(R.string.app_id_example));

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.Contact_adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                //  .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);
    }
}
