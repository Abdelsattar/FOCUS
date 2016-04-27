package abdelsattar.com.focus.Activties;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import abdelsattar.com.focus.R;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button todoB = (Button) findViewById(R.id.Main_ToDoListB);
        Button contact = (Button) findViewById(R.id.Main_contactB);
        Button thankful = (Button) findViewById(R.id.Main_thankful);
        Button top3 = (Button) findViewById(R.id.Main_top3B);
        todoB.setOnClickListener(this);
        contact.setOnClickListener(this);
        thankful.setOnClickListener(this);
        top3.setOnClickListener(this);

       // this.deleteDatabase(Constants.DATABASE_NAME);

    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.Main_ToDoListB:{
                intent = new Intent(this, ToDoList.class);
                break;
            }
            case R.id.Main_top3B:{
                intent = new Intent(this, TopTasks.class);
                break;
            }
            case R.id.Main_contactB:{
                intent = new Intent(this, Contact.class);
                break;
            }
            case R.id.Main_thankful:{
                intent = new Intent(this, Thankful.class);
                break;
            }

        }
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
