package abdelsattar.com.focusII.Activties;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import abdelsattar.com.focusII.Constants;
import abdelsattar.com.focusII.DataBase.SharedPreferenceHelper;
import abdelsattar.com.focusII.R;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

import static abdelsattar.com.focusII.R.id.Main_contactSection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindViews({R.id.Main_ToDoListB, R.id.Main_contactB, R.id.Main_thankful, R.id.Main_top3B})
    List<ImageView> mainButtons;

    @BindViews({R.id.sagda_Image, R.id.sagda_Image2, R.id.sagda_Image3, R.id.sagda_Image4, R.id.sagda_Image5})
    List<ImageView> sagdaImages;

    @BindViews({R.id.Main_toDoTV, R.id.Main_contactTV, R.id.Main_thankfulTV, R.id.Main_top3BtV})
    List<TextView> mainTexts;

    @BindViews({R.id.Main_todoSection, Main_contactSection, R.id.Main_thankfulSection, R.id.Main_top3Section})
    List<LinearLayout> mainSections;

    @BindView(R.id.Main_linear1)
    LinearLayout linearLayout1;

    @BindView(R.id.Main_linear2)
    LinearLayout linearLayout2;

    SharedPreferenceHelper prefHelper;
    boolean[] isPressedSagda;

    Animation animFadein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initScreen();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Main_ToDoListB: {
                gotoActivity(ToDoList.class);
                break;
            }
            case R.id.Main_top3B: {
                gotoActivity(TopTasks.class);
                break;
            }
            case R.id.Main_contactB: {
                gotoActivity(Contact.class);
                break;
            }
            case R.id.Main_thankful: {
                gotoActivity(Thankful.class);
                break;
            }

            case R.id.Main_top3BtV: {
                gotoActivity(TopTasks.class);
                break;
            }
            case R.id.Main_toDoTV: {
                gotoActivity(ToDoList.class);
                break;
            }
            case R.id.Main_contactTV: {
                gotoActivity(Contact.class);
                break;
            }
            case R.id.Main_thankfulTV: {
                gotoActivity(Thankful.class);
                break;
            }

            case R.id.sagda_Image: {
                colorSagdaImage(0);
                break;
            }
            case R.id.sagda_Image2: {
                colorSagdaImage(1);
                break;
            }
            case R.id.sagda_Image3: {
                colorSagdaImage(2);
                break;
            }
            case R.id.sagda_Image4: {
                colorSagdaImage(3);
                break;
            }
            case R.id.sagda_Image5: {
                colorSagdaImage(4);
                break;
            }
        }
    }

    @Override
    protected void onStop() {
        saveSagdaSate();
        super.onStop();
    }


    private void saveSagdaSate() {
        SharedPreferences prefs = this.getSharedPreferences(Constants.KEY_PREF_SAGDA, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        for (int i = 0; i < isPressedSagda.length; i++) {
            Log.e("Sagda Status", " bool : " + isPressedSagda[i] + "");
            if (isPressedSagda[i]) {
                sagdaImages.get(i).setImageResource(R.drawable.green);
            }
        }
        String jsonText = gson.toJson(isPressedSagda);
        Log.e("Sagda Status", " json: " + jsonText);
        editor.putString(Constants.KEY_PREF_SAGDA_STATUS, jsonText);
        editor.apply();
    }

    public void getSagdaStatus() {
        SharedPreferences prefs = this.getSharedPreferences(Constants.KEY_PREF_SAGDA, MODE_PRIVATE);

        Gson gson = new Gson();
        String jsonText = prefs.getString(Constants.KEY_PREF_SAGDA_STATUS, null);
        if (jsonText != null) {
            isPressedSagda = gson.fromJson(jsonText, boolean[].class);
            Log.e("Sagda Status", "it is not null");
        } else {
            Log.e("Sagda Status", "it is null");
            isPressedSagda = new boolean[5];
            for (int i = 0; i < isPressedSagda.length; i++) {
                isPressedSagda[i] = false;
            }
        }
        //  isPressedSagda = prefHelper.retrieveSagdaState(this);
        for (int i = 0; i < isPressedSagda.length; i++) {
            Log.e("Sagda Status", " bool : " + isPressedSagda[i] + "");
            if (isPressedSagda[i]) {
                sagdaImages.get(i).setImageResource(R.drawable.green);
            }
        }

    }

    private void gotoActivity(Class classType) {
        Intent intent = new Intent(this, classType);
        startActivity(intent);
    }

    private void colorSagdaImage(int pos) {
        if (isPressedSagda[pos])
            sagdaImages.get(pos).setImageResource(R.drawable.white);
        else
            sagdaImages.get(pos).setImageResource(R.drawable.green);

        isPressedSagda[pos] = !isPressedSagda[pos];
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_about) {
//            Intent intent = new Intent(this, About.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void initScreen() {
        ButterKnife.bind(this);

        startAnimation();
        getSagdaStatus();

        prefHelper = new SharedPreferenceHelper();
        for (int i = 0; i < mainButtons.size(); i++) {
            mainButtons.get(i).setOnClickListener(this);
        }
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Brandon.ttf");

        for (int i = 0; i < mainTexts.size(); i++) {
            mainTexts.get(i).setTypeface(type);
            mainTexts.get(i).setOnClickListener(this);
        }

        for (int i = 0; i < sagdaImages.size(); i++) {
            sagdaImages.get(i).setOnClickListener(this);
        }

        final ArrayList<Class>  activities = new ArrayList<>();
        activities.add(ToDoList.class);
        activities.add(Contact.class);
        activities.add(Thankful.class);
        activities.add(TopTasks.class);

        for (int i = 0; i < mainSections.size(); i++) {
            final int l = i;
            mainSections.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gotoActivity(activities.get(l));
                }
            });
        }
    }

    private void startAnimation() {
        animFadein = AnimationUtils.loadAnimation(this, R.anim.enter_to_top);
        linearLayout1.startAnimation(animFadein);
//        animFadein.setDuration(1500);
        linearLayout2.startAnimation(animFadein);
    }

    public void hello() {
/*
        ImageView todoB = (ImageView) findViewById(R.id.Main_ToDoListB);
        ImageView contact = (ImageView) findViewById(R.id.Main_contactB);
        ImageView thankful = (ImageView) findViewById(R.id.Main_thankful);
        ImageView top3 = (ImageView) findViewById(R.id.Main_top3B);
        todoB.setOnClickListener(this);
        contact.setOnClickListener(this);
        thankful.setOnClickListener(this);
        top3.setOnClickListener(this);

        TextView top33 = (TextView) findViewById(R.id.Main_top3BtV);
        TextView toDo = (TextView) findViewById(R.id.Main_toDoTV);
        TextView contactTv = (TextView) findViewById(R.id.Main_contactTV);
        TextView thankfulTv = (TextView) findViewById(R.id.Main_thankfulTV);
        toDo.setTypeface(type);
        contactTv.setTypeface(type);
        thankfulTv.setTypeface(type);
        top33.setTypeface(type);

        toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ToDoList.class);
                startActivity(intent);
            }
        });

        contactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Contact.class);
                startActivity(intent);
            }
        });
        thankfulTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), Thankful.class);
                startActivity(intent);
            }
        });
        top33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplication(), TopTasks.class);
                startActivity(intent);
            }
        });

//        sagda = (ImageView) findViewById(R.id.sagda_Image);
//        sagda2 = (ImageView) findViewById(R.id.sagda_Image2);
//        sagda3 = (ImageView) findViewById(R.id.sagda_Image3);
//        sagda4 = (ImageView) findViewById(R.id.sagda_Image4);
//        sagda5 = (ImageView) findViewById(R.id.sagda_Image5);
//        sagda.setOnClickListener(this);
//        sagda2.setOnClickListener(this);
//        sagda3.setOnClickListener(this);
//        sagda4.setOnClickListener(this);
//        sagda5.setOnClickListener(this);
        // this.deleteDatabase(Constants.DATABASE_NAME);
        */

    }
}