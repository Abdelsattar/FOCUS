package abdelsattar.com.focusII.Activties;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import abdelsattar.com.focusII.Adapters.RecycleViewAdapterSubTasks;
import abdelsattar.com.focusII.DataBase.DatabaseHelper;
import abdelsattar.com.focusII.Model.SubTask;
import abdelsattar.com.focusII.R;

public class subtasksDetails extends AppCompatActivity {

    ArrayList<SubTask> subTasks;
    DatabaseHelper db;
    FloatingActionButton fab;
    Toolbar toolbar;
    RecyclerView recyclerView;
    long parent_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtasks_details);
        parent_id = (long) getIntent().getExtras().get("parent_id");

        initializeScreen(parent_id);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupAddTaskDialog();
            }
        });
    }


    private void  initializeScreen(long id){
        fab = (FloatingActionButton) findViewById(R.id.SubTasks_fab);
        recyclerView = (RecyclerView) findViewById(R.id.SubTasks_recycleView);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        db = new DatabaseHelper(getApplicationContext());
        subTasks = db.getAllSubTasks(id);

        initializeRecycleViewAdapter();

    }


    private void initializeRecycleViewAdapter(){
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        RecycleViewAdapterSubTasks adapter = new RecycleViewAdapterSubTasks(this, subTasks);
        recyclerView.setAdapter(adapter);
    }
    private void setupAddTaskDialog() {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.add_task_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setView(promptsView);

        final EditText taskDialog = (EditText) promptsView
                .findViewById(R.id.task_Dialog);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (taskDialog.getText().toString().trim().equalsIgnoreCase("")) {
                                    taskDialog.setError("This field can not be blank");
                                } else {
                                    long taskId = db.createSubTask(new SubTask(parent_id,taskDialog.getText().toString()));
                                    subTasks.add(new SubTask(parent_id,taskDialog.getText().toString()));
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
