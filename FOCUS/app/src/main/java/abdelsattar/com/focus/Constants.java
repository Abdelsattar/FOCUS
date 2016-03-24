package abdelsattar.com.focus;

/**
 * Created by lenovo on 25/03/2016.
 */
public class Constants {

    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "contactsManager";

    // Table Names
    public static final String TABLE_TASK = "tasks";
    public static final String TABLE_TAG = "tags";
    public static final String TABLE_TODO_TAG = "todo_tags";

    // Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_CREATED_AT = "created_at";

    // NOTES Table - column nmaes
    public static final String KEY_TASK = "task";

    public static final String KEY_STATUS = "status";

    // TAGS Table - column names
    public static final String KEY_TAG_NAME = "tag_name";

    // NOTE_TAGS Table - column names
    public static final String KEY_TODO_ID = "todo_id";
    public static final String KEY_TAG_ID = "tag_id";

    // Table Create Statements
    // Todo table create statement
    public static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TASK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TASK
            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Tag table create statement
    public static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // todo_tag table create statement
    public static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

}
