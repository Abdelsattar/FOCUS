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
import abdelsattar.com.focus.Model.Task;

/**
 * Created by lenovo on 25/03/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
     * Creating a task
     */
    public long createTasks(Task task, long[] tag_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TASK, task.getTask());

        // insert row
        long taskID = db.insert(Constants.TABLE_TASK, null, values);

        return taskID;
    }


    /**
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
     * getting all tasks
     */
    public List<Task> getAllToDos() {
        List<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_TASK;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Task td = new Task();
                td.setId(c.getInt((c.getColumnIndex(Constants.KEY_ID))));
                td.setTask((c.getString(c.getColumnIndex(Constants.KEY_TASK))));

                // adding to task list
                tasks.add(td);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
     * getting all tasks under single tag
     */
    public List<Task> getAllToDosByTag(String tag_name) {
        List<Task> tasks = new ArrayList<Task>();

        String selectQuery = "SELECT  * FROM " + Constants.TABLE_TASK + " td, "
                + Constants.TABLE_TAG + " tg, " + Constants.TABLE_TODO_TAG + " tt WHERE tg."
                + Constants.KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + Constants.KEY_ID
                + " = " + "tt." + Constants.KEY_TAG_ID + " AND td." + Constants.KEY_ID + " = "
                + "tt." + Constants.KEY_TODO_ID;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Task td = new Task();
                td.setId(c.getInt((c.getColumnIndex(Constants.KEY_ID))));
                td.setTask((c.getString(c.getColumnIndex(Constants.KEY_TASK))));

                // adding to task list
                tasks.add(td);
            } while (c.moveToNext());
        }

        return tasks;
    }

    /**
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
     * Deleting a task
     */
    public void deleteTask(long tado_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_TASK, Constants.KEY_ID + " = ?",
                new String[]{String.valueOf(tado_id)});
    }

    // ------------------------ "tags" table methods ----------------//
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
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
