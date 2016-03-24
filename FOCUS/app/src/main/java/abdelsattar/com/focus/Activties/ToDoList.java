package abdelsattar.com.focus.Activties;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import abdelsattar.com.focus.Adapters.RecycleViewAdapter;
import abdelsattar.com.focus.Adapters.RecyclerItemClickListener;
import abdelsattar.com.focus.R;

public class ToDoList extends AppCompatActivity {
    ArrayList <String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeData();

        RecycleViewAdapter adapter = new RecycleViewAdapter(tasks);
        rv.setAdapter(adapter);


        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent =new Intent(getApplication(), MainActivity.class);
                        Log.d("LOOL", position+"");
                        startActivity(intent);
                    }
                })
        );
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLocatemeDialog();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void initializeData(){
        tasks = new ArrayList<>();
        tasks.add("ready some books");
        tasks.add("pray for allh");
        tasks.add("See some narto pieces");
        tasks.add("try to read quran");

    }

    private void setupLocatemeDialog() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.subset_tasks_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setView(promptsView);

//        final EditText userLatitude = (EditText) promptsView
//                .findViewById(R.id.locatemeDialog_latitudeET);
//        final EditText userLongitude = (EditText) promptsView
//                .findViewById(R.id.locatemeDialog_longitudeET);


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("locate",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


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
