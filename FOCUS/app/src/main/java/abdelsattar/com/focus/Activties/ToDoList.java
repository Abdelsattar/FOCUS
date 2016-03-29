package abdelsattar.com.focus.Activties;

import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
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
import java.util.List;

import abdelsattar.com.focus.Adapters.RecycleViewAdapter;
import abdelsattar.com.focus.Adapters.RecyclerItemClickListener;
import abdelsattar.com.focus.DataBase.DatabaseHelper;
import abdelsattar.com.focus.Model.SubTask;
import abdelsattar.com.focus.Model.Task;
import abdelsattar.com.focus.R;

public class ToDoList extends AppCompatActivity {
    ArrayList <String> tasks;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(getApplicationContext());
        testDB();

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

    void  testDB(){

        // Inserting tags in db
        long task1_id = db.createTask(new Task(" Study"));
        long task2_id = db.createTask(new Task("Learn"));
        long task3_id = db.createTask(new Task("Work"));

        Log.d("Tag Count", "Tag Count: " + db.getAllTasks().size());

        // Creating ToDos
        SubTask subTask1 = new SubTask(task1_id,"Doing Compiler Assignemnt");
        SubTask subTask2 = new SubTask(task1_id,"Doing Parallel Assignemnt");

        SubTask subTask3 = new SubTask(task2_id,"Learn Firebase");
        SubTask subTask4 = new SubTask(task2_id,"learn franch language");

        SubTask subTask5 = new SubTask(task3_id,"FOCUS ON FOUCS");
        SubTask subTask6 = new SubTask(task3_id,"Quandoe Lib");

        // Inserting todos in db
        // Inserting todos under "Shopping" Tag
        long subTask1_id = db.createSubTask(subTask1);
        long subTask2_id = db.createSubTask(subTask2);
        long subTask3_id = db.createSubTask(subTask3);
        long subTask4_id = db.createSubTask(subTask4);
        long subTask5_id = db.createSubTask(subTask5);
        long subTask6_id = db.createSubTask(subTask6);

        Log.e("Todo Count", "Todo count: " + db.getSubTaskCount());


        // Getting all Todos
        Log.d("Get Todos", "Getting All ToDos");

        List<Task> allToDos = db.getAllTasks();
        for (Task todo : allToDos) {
            Log.d("ToDo", todo.getTask());
        }

        // Getting todos under "Watchlist" tag name
        Log.d("ToDo", "Get todos under single Tag name");

        List<SubTask> tagsWatchList = db.getAllSubTasks(task1_id);
        for (SubTask todo : tagsWatchList) {
            Log.d("ToDo Watchlist", todo.getSubTask());
        }

        // Deleting a ToDo
        Log.d("Delete ToDo", "Deleting a Todo");
        Log.d("Tag Count", "Tag Count Before Deleting: " + db.getTaskCount());

        db.deleteTask(task2_id);

        Log.d("Tag Count", "Tag Count After Deleting: " + db.getTaskCount());

        // Deleting all Todos under "Shopping" tag
        Log.d("Tag Count",
                "Tag Count Before Deleting 'Shopping' Todos: "
                        + db.getSubTaskCount());

        db.deleteSubTask(subTask1_id);

        Log.d("Tag Count",
                "Tag Count After Deleting 'Shopping' Todos: "
                        + db.getSubTaskCount());

        // Updating tag name
        subTask1.setSubTask("Hello from the other side");
        db.updateSubTask(subTask1);

        // Don't forget to close database connection
        db.closeDB();
    }
}
