package abdelsattar.com.focusII.Activties;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import abdelsattar.com.focusII.Adapters.ThankfulRecycleViewAdapter;
import abdelsattar.com.focusII.DataBase.DatabaseHelper;
import abdelsattar.com.focusII.Model.ThankfulFor;
import abdelsattar.com.focusII.R;

public class Thankful extends AppCompatActivity {

    private FloatingActionButton fab;
    private DatabaseHelper db;
    private ArrayList<ThankfulFor> thankfulFor;
    private RecyclerView recyclerView;
    private ThankfulRecycleViewAdapter adapter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankful);
        initScreen();
    }

    private void initScreen() {
        setupGoogleAds();

        thankfulFor = new ArrayList<>();
        fab = (FloatingActionButton) findViewById(R.id.Thankful_Add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupAddTaskDialog();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.Thankful_recycleView);
        db = new DatabaseHelper(getApplicationContext());
        thankfulFor = db.getAllThankfulFor();

        initializeRecycleViewAdapter();
    }

    private void initializeRecycleViewAdapter() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        adapter = new ThankfulRecycleViewAdapter(this, thankfulFor);

        recyclerView.setAdapter(adapter);
    }

    private void setupAddTaskDialog() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_edit_text, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this, R.style.InvitationDialog);

        alertDialogBuilder.setView(promptsView);

        final EditText thankfulDialog = (EditText) promptsView
                .findViewById(R.id.DialogET);

        alertDialogBuilder.setTitle("Adding Thankful");

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (thankfulDialog.getText().toString().trim().equalsIgnoreCase("")) {
                                    thankfulDialog.setError("This field can not be blank");
                                } else {
                                    long thankfulForID = db.createThankful(thankfulDialog.getText().toString());
                                    thankfulFor.add(new ThankfulFor(thankfulForID, thankfulDialog.getText().toString()));
                                    adapter.notifyDataSetChanged();

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
        // TODO change the id to production
        MobileAds.initialize(getApplicationContext(),getString(R.string.app_id_example));

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.Thankful_adView);
        mAdView.setVisibility(View.INVISIBLE);

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
