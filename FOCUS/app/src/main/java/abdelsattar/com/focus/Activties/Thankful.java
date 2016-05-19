package abdelsattar.com.focus.Activties;

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

import java.util.ArrayList;

import abdelsattar.com.focus.Adapters.ThankfulRecycleViewAdapter;
import abdelsattar.com.focus.DataBase.DatabaseHelper;
import abdelsattar.com.focus.Model.ThankfulFor;
import abdelsattar.com.focus.R;

public class Thankful extends AppCompatActivity {

    FloatingActionButton fab;
    DatabaseHelper db;
    ArrayList<ThankfulFor> thankfulFor;
    RecyclerView recyclerView;
    ThankfulRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankful);

        thankfulFor = new ArrayList<>();
        fab = (FloatingActionButton) findViewById(R.id.Thankful_Add);
       // fab.setBackgroundColor(getResources().getColor(R.color.white));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupAddTaskDialog();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.Thankful_recycleView);
        db = new DatabaseHelper(getApplicationContext());

        thankfulFor = db.getAllThankfulFor();
        //db.getAllTasks();
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
        View promptsView = li.inflate(R.layout.thankfull_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setView(promptsView);

        final EditText thankfulDialog = (EditText) promptsView
                .findViewById(R.id.Thankful_String);

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

}
