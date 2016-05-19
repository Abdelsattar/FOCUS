package abdelsattar.com.focus.Activties;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import abdelsattar.com.focus.Constants;
import abdelsattar.com.focus.R;

public class TopTasks extends AppCompatActivity implements View.OnClickListener {

    ImageView editOne, editTwo, editThree;
    TextView top1, top2, top3;
    String AddedString;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tasks);
        initializeScreen();
        editOne.setOnClickListener(this);
        editTwo.setOnClickListener(this);
        editThree.setOnClickListener(this);

        getDataFromSharedPreferences();

    }

    private void getDataFromSharedPreferences() {
        sharedPreferences = getSharedPreferences(Constants.KEY_SharedPreference, Context.MODE_PRIVATE);
        String one, two, three;
        one = sharedPreferences.getString(Constants.KEY_Pref_one, null);
        two = sharedPreferences.getString(Constants.KEY_Pref_two, null);
        three = sharedPreferences.getString(Constants.KEY_Pref_three, null);

        if (one != null) {
            top1.setText(one);
        }
        if (two != null) {
            top2.setText(two);
        }
        if (three != null) {
            top3.setText(three);
        }
    }

    public void initializeScreen() {
        top1 = (TextView) findViewById(R.id.Top3_one);
        top2 = (TextView) findViewById(R.id.Top3_two);
        top3 = (TextView) findViewById(R.id.Top3_three);

        editOne = (ImageView) findViewById(R.id.EditOne);
        editTwo = (ImageView) findViewById(R.id.EditTwo);
        editThree = (ImageView) findViewById(R.id.EditThree);


    }

    @Override
    public void onClick(View view) {


        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (view.getId()) {
            case R.id.EditOne: {
                setupAddTaskDialog(1);
                break;
            }
            case R.id.EditTwo: {
                setupAddTaskDialog(2);
                break;
            }
            case R.id.EditThree: {
                setupAddTaskDialog(3);
                break;
            }
        }
        editor.commit();
    }


    private void setupAddTaskDialog(final int num) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.add_top_task, null);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setView(promptsView);

        final EditText taskDialog = (EditText) promptsView
                .findViewById(R.id.Top_Dialog);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (taskDialog.getText().toString().trim().equalsIgnoreCase("")) {
                                    taskDialog.setError("This field can not be blank");
                                } else {
                                    AddedString = taskDialog.getText().toString();
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    if (num == 1) {
                                        editor.putString(Constants.KEY_Pref_one, AddedString);
                                        top1.setText(AddedString);
                                    } else if (num == 2) {

                                        editor.putString(Constants.KEY_Pref_two, AddedString);
                                        top2.setText(AddedString);

                                    } else if (num == 3) {
                                        editor.putString(Constants.KEY_Pref_three, AddedString);
                                        top3.setText(AddedString);

                                    }
                                    editor.commit();
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
