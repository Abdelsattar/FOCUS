package abdelsattar.com.focusII.Activties;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.List;

import abdelsattar.com.focusII.Constants;
import abdelsattar.com.focusII.DataBase.SharedPreferenceHelper;
import abdelsattar.com.focusII.R;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindViews({R.id.sagda_Image, R.id.sagda_Image2, R.id.sagda_Image3, R.id.sagda_Image4, R.id.sagda_Image5})
    List<ImageView> sagdaImages;


    SharedPreferenceHelper prefHelper;
    boolean[] isPressedSagda;

    CardView top3CV, todoCV, thankfulCV, contactCV;

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
            case R.id.main_top3CV: {
                gotoActivity(TopTasks.class);
                break;
            }
            case R.id.main_todoCV: {
                gotoActivity(ToDoList.class);
                break;
            }
            case R.id.main_thankfulCV: {
                gotoActivity(Thankful.class);
                break;
            }
            case R.id.main_contactCV: {
                gotoActivity(Contact.class);
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

        getSagdaStatus();

        prefHelper = new SharedPreferenceHelper();

        for (int i = 0; i < sagdaImages.size(); i++) {
            sagdaImages.get(i).setOnClickListener(this);
        }

        top3CV = (CardView) findViewById(R.id.main_top3CV);
        contactCV = (CardView) findViewById(R.id.main_contactCV);
        thankfulCV = (CardView) findViewById(R.id.main_thankfulCV);
        todoCV = (CardView) findViewById(R.id.main_todoCV);

        top3CV.setOnClickListener(this);
        todoCV.setOnClickListener(this);
        thankfulCV.setOnClickListener(this);
        contactCV.setOnClickListener(this);

//        MaterialRippleLayout.on(contactCV)
//                .rippleColor(Color.BLACK)
//                .create();
//        RippleForegroundListener rippleForegroundListener = new RippleForegroundListener();
//        rippleForegroundListener.setCardView(top3CV);
//        top3CV.setOnTouchListener(rippleForegroundListener);

    }

//    private void startAnimation() {
//        animFadein = AnimationUtils.loadAnimation(this, R.anim.enter_to_top);
//        linearLayout1.startAnimation(animFadein);
////        animFadein.setDuration(1500);
//        linearLayout2.startAnimation(animFadein);
//    }

}