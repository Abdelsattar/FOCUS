package abdelsattar.com.focusII.Activties;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import abdelsattar.com.focusII.Adapters.RecycleViewAdapter;
import abdelsattar.com.focusII.DataBase.DatabaseHelper;
import abdelsattar.com.focusII.Model.Task;
import abdelsattar.com.focusII.R;

public class ToDoList extends AppCompatActivity {
    private ArrayList<Task> tasks;
    private DatabaseHelper db;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        initializeScreen();
    }

    private void initializeScreen() {
        setupGoogleAds();
        fab = (FloatingActionButton) findViewById(R.id.Todo_fab);
        recyclerView = (RecyclerView) findViewById(R.id.Todo_recycleView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(getApplicationContext());
        tasks = db.getAllTasks();
        initializeRecycleViewAdapter();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupAddTaskDialog();
            }
        });
    }

    private void setupGoogleAds() {

        MobileAds.initialize(getApplicationContext(), getString(R.string.app_id_example));
        mAdView = (AdView) findViewById(R.id.Todo_adView);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mAdView.loadAd(adRequest);
    }

    private void initializeRecycleViewAdapter() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecycleViewAdapter(this, tasks);
        recyclerView.setAdapter(adapter);
    }

    private void initializeDummyData() {
        ArrayList<String> ts = new ArrayList<>();
        ts.add("ready some books");
        ts.add("pray for allh");
        ts.add("See some narto pieces");
        ts.add("try to read quran");
    }

    private void setupAddTaskDialog() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_edit_text, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this, R.style.InvitationDialog);
        alertDialogBuilder.setTitle("Adding new Task");

        alertDialogBuilder.setView(promptsView);

        final EditText taskDialog = (EditText) promptsView
                .findViewById(R.id.DialogET);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (taskDialog.getText().toString().trim().equalsIgnoreCase("")) {
                                    taskDialog.setError("This field can not be blank");
                                } else {
                                    long taskId = db.createTask(new Task(taskDialog.getText().toString()));
                                    tasks.add(new Task(taskId, taskDialog.getText().toString()));
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

}
