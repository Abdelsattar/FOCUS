package abdelsattar.com.focusII;

import android.content.Context;
import android.util.Log;

import java.util.List;

import abdelsattar.com.focusII.DataBase.DatabaseHelper;
import abdelsattar.com.focusII.Model.SubTask;
import abdelsattar.com.focusII.Model.Task;

/**
 * Created by lenovo on 25/03/2016.
 */
public class Constants {

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "contactsManager";

    // Table Names
    public static final String TABLE_THANKFUL = "thankful";
    public static final String TABLE_TASK = "tasks";
    public static final String TABLE_Sub_TASK = "sub_tasks";

    // NOTES Table - column nmaes
    public static final String KEY_THANKFUL = "thankful_for";

    public static final String KEY_TASK = "task";
    public static final String KEY_ID = "id";

    public static final String KEY_SUB_TASK = "sub_task";
    public static final String KEY_SUB_TASK_PARENT_ID = "parent_id";


    //Shared Prefrenece

    public static final String KEY_PREF_SAGDA = "sagda_sharedPref";
    public static final String KEY_PREF_SAGDA_STATUS = "sagda_status";

    public static final String KEY_SharedPreference = "TopPreferences";

    public static final String[] personsKeyRef = {"firstPerson","secondPerson","thirdPerson"};


    public static final String KEY_Pref_one = "TopOne";
    public static final String KEY_Pref_two = "TopTwo";
    public static final String KEY_Pref_three = "TopThree";

    public static final String KEY_Pref_1st_person = "firstPerson";
    public static final String KEY_Pref_2nd_person = "secondPerson";
    public static final String KEY_Pref_3rd_person = "thirdPerson";


    // Table Create Statements

    public static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TABLE_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TASK
            + " TEXT )";

    public static final String CREATE_TABLE_SUB_TASK = "CREATE TABLE "
            + TABLE_Sub_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SUB_TASK
            + " TEXT," + KEY_SUB_TASK_PARENT_ID + "  INTEGER )";

    public static final String CREATE_TABLE_THANKFUL = "CREATE TABLE "
            + TABLE_THANKFUL + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_THANKFUL
            + " TEXT )";

    void testDB() {

        Context context = null; // the application context
        DatabaseHelper db = new DatabaseHelper(context);
        // Inserting tags in db
        long task1_id = db.createTask(new Task("Study"));
        long task2_id = db.createTask(new Task("Learn"));
        long task3_id = db.createTask(new Task("Work"));

        Log.d("Tag Count", "Tag Count: " + db.getAllThankfulFor().size());

        // Creating ToDos
        SubTask subTask1 = new SubTask(task1_id, "Doing Compiler Assignemnt");
        SubTask subTask2 = new SubTask(task1_id, "Doing Parallel Assignemnt");

        SubTask subTask3 = new SubTask(task2_id, "Learn Firebase");
        SubTask subTask4 = new SubTask(task2_id, "learn franch language");

        SubTask subTask5 = new SubTask(task3_id, "FOCUS ON FOUCS");
        SubTask subTask6 = new SubTask(task3_id, "Quandoe Lib");

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
