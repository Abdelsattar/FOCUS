package abdelsattar.com.focus.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import abdelsattar.com.focus.Constants;
import abdelsattar.com.focus.Model.SubTask;
import abdelsattar.com.focus.Model.Task;

/**
 * Created by lenovo on 25/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE_TASK);
        db.execSQL(Constants.CREATE_TABLE_SUB_TASK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_Sub_TASK);

        onCreate(db);
    }

    /**
     * TODO TRUE
     * Creating a task
     */
    public long createTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TASK, task.getTask());

        long taskID = db.insert(Constants.TABLE_TASK, null, values);

        return taskID;
    }


    /**
     * TODO TRUE
     * get single task
     */
    public Task getTask(long taskID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Constants.TABLE_TASK + " WHERE "
                + Constants.KEY_ID + " = " + taskID;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Task td = new Task();
        td.setId(c.getInt(c.getColumnIndex(Constants.KEY_ID)));
        td.setTask((c.getString(c.getColumnIndex(Constants.KEY_TASK))));

        return td;
    }

    /**
     * TODO TRUE
     * getting all tasks
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_TASK;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Task td = new Task();
                td.setId(c.getInt((c.getColumnIndex(Constants.KEY_ID))));
                td.setTask((c.getString(c.getColumnIndex(Constants.KEY_TASK))));

                tasks.add(td);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
     * TODO TRUE
     * getting task count
     */
    public int getTaskCount() {
        String countQuery = "SELECT  * FROM " + Constants.TABLE_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * TODO TRUE
     * Updating a task
     */
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TASK, task.getTask());

        // updating row
        return db.update(Constants.TABLE_TASK, values, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }

    /**
     * TODO TRUE
     * Deleting a task
     */
    public void deleteTask(long taskID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_TASK, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(taskID)});
    }

    // ------------------------ "SubTasks" table methods ----------------//
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * DONE
     * Creating a task
     */
    public long createSubTask(SubTask subTask) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_SUB_TASK, subTask.getSubTask());
        values.put(Constants.KEY_SUB_TASK_PARENT_ID, subTask.getParent_ID());

        long taskID = db.insert(Constants.TABLE_Sub_TASK, null, values);

        return taskID;
    }

    /**
     * DONE
     * get single SubTask
     */
    public SubTask getSubTask(long subTaskID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Constants.TABLE_Sub_TASK + " WHERE "
                + Constants.KEY_ID + " = " + subTaskID;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        SubTask td = new SubTask();
        td.setId(c.getInt(c.getColumnIndex(Constants.KEY_ID)));
        td.setSubTask(c.getString(c.getColumnIndex(Constants.KEY_SUB_TASK)));

        return td;
    }

    /**
     * DONE
     * getting all SubTasks based on Task
     */
    public List<SubTask> getAllSubTasks(long parentTaskID) {
        List<SubTask> tasks = new ArrayList<SubTask>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_Sub_TASK + " WHERE "
                + Constants.KEY_SUB_TASK_PARENT_ID + " = " + parentTaskID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                SubTask td = new SubTask();
                td.setId(c.getInt((c.getColumnIndex(Constants.KEY_ID))));
                td.setSubTask((c.getString(c.getColumnIndex(Constants.KEY_SUB_TASK))));
               // td.setParent_ID();
                tasks.add(td);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
     * DONE
     * getting subTask count
     */
    public int getSubTaskCount() {
        String countQuery = "SELECT  * FROM " + Constants.TABLE_Sub_TASK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /**
     * DONE
     * Updating a SubTask
     */
    public int updateSubTask(SubTask subTask) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_SUB_TASK, subTask.getSubTask());

        return db.update(Constants.TABLE_Sub_TASK, values, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(subTask.getId())});
    }

    /**
     * DONE
     * Deleting a task
     */
    public void deleteSubTask(long subTaskID) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_Sub_TASK, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(subTaskID)});
    }



    /**
     * get datetime
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

}
