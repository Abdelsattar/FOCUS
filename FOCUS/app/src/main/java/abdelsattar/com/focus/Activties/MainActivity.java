package abdelsattar.com.focus.Activties;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import abdelsattar.com.focus.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean isPressed = false;
    boolean isPressed2 = false;
    boolean isPressed3 = false;
    boolean isPressed4 = false;
    boolean isPressed5 = false;
    ImageView sagda, sagda2, sagda3, sagda4, sagda5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView todoB = (ImageView) findViewById(R.id.Main_ToDoListB);
        ImageView contact = (ImageView) findViewById(R.id.Main_contactB);
        ImageView thankful = (ImageView) findViewById(R.id.Main_thankful);
        ImageView top3 = (ImageView) findViewById(R.id.Main_top3B);
        todoB.setOnClickListener(this);
        contact.setOnClickListener(this);
        thankful.setOnClickListener(this);
        top3.setOnClickListener(this);


        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/Brandon.ttf");

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
                Intent  intent = new Intent(getApplication(), ToDoList.class);
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

                Intent  intent = new Intent(getApplication(), TopTasks.class);
                startActivity(intent);
            }
        });
/*
        LinearLayout top3Sectsetion = (LinearLayout) findViewById(R.id.Main_top3Section);
        LinearLayout todoSectsetion = (LinearLayout) findViewById(R.id.Main_todoSection);
        LinearLayout contactSectsetion = (LinearLayout) findViewById(R.id.Main_contactSection);
        LinearLayout thankfulSectsetion = (LinearLayout) findViewById(R.id.Main_thankfulSection);

        top3Sectsetion.setOnClickListener(this);
        todoSectsetion.setOnClickListener(this);
        contactSectsetion.setOnClickListener(this);
        thankfulSectsetion.setOnClickListener(this);*/

        sagda = (ImageView) findViewById(R.id.sagda_Image);
        sagda2 = (ImageView) findViewById(R.id.sagda_Image2);
        sagda3 = (ImageView) findViewById(R.id.sagda_Image3);
        sagda4 = (ImageView) findViewById(R.id.sagda_Image4);
        sagda5 = (ImageView) findViewById(R.id.sagda_Image5);
        sagda.setOnClickListener(this);
        sagda2.setOnClickListener(this);
        sagda3.setOnClickListener(this);
        sagda4.setOnClickListener(this);
        sagda5.setOnClickListener(this);
        // this.deleteDatabase(Constants.DATABASE_NAME);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.Main_ToDoListB: {
                Intent intent = new Intent(this, ToDoList.class);
                startActivity(intent);
                break;
            }
            case R.id.Main_top3B: {
                Intent  intent = new Intent(this, TopTasks.class);
                startActivity(intent);
                break;
            }
            case R.id.Main_contactB: {
                Intent intent = new Intent(this, Contact.class);
                startActivity(intent);
                break;
            }
            case R.id.Main_thankful: {
                Intent intent = new Intent(this, Thankful.class);
                startActivity(intent);
                break;
            }
            case R.id.sagda_Image: {
                if (isPressed)
                    sagda.setImageResource(R.drawable.white);
                else
                    sagda.setImageResource(R.drawable.green);
                isPressed = !isPressed;
                break;
            }
            case R.id.sagda_Image2: {
                if (isPressed2)
                    sagda2.setImageResource(R.drawable.white);
                else
                    sagda2.setImageResource(R.drawable.green);
                isPressed2 = !isPressed2;
                break;
            }
            case R.id.sagda_Image3: {
                if (isPressed3)
                    sagda3.setImageResource(R.drawable.white);
                else
                    sagda3.setImageResource(R.drawable.green);
                isPressed3 = !isPressed3;
                break;
            }
            case R.id.sagda_Image4: {
                if (isPressed4)
                    sagda4.setImageResource(R.drawable.white);
                else
                    sagda4.setImageResource(R.drawable.green);
                isPressed4 = !isPressed4;
                break;
            }
            case R.id.sagda_Image5: {
                if (isPressed5)
                    sagda5.setImageResource(R.drawable.white);
                else
                    sagda5.setImageResource(R.drawable.green);
                isPressed5 = !isPressed5;
                break;
            }
//            case R.id.Main_top3BtV: {
//                Intent  intent = new Intent(this, TopTasks.class);
//                startActivity(intent);
//            }
//            case R.id.Main_toDoTV: {
//                Intent  intent = new Intent(this, ToDoList.class);
//                startActivity(intent);
//            }
//            case R.id.Main_contactTV: {
//                Intent intent = new Intent(this, Contact.class);
//                startActivity(intent);
//            }
//            case R.id.Main_thankfulTV: {
//                Intent intent = new Intent(this, Thankful.class);
//                startActivity(intent);
//            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
