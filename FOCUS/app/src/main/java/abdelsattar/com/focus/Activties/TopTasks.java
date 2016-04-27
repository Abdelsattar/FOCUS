package abdelsattar.com.focus.Activties;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import abdelsattar.com.focus.R;

public class TopTasks extends AppCompatActivity {

    ImageView editOne,editTwo,editThree;
    TextView top1,top2,top3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_tasks);

    }

    public void initializeScreen(){
        top1 = (TextView) findViewById(R.id.one);
        top2 = (TextView) findViewById(R.id.two);
        top3 = (TextView) findViewById(R.id.three);

        editOne = (ImageView) findViewById(R.id.EditOne);
        editTwo= (ImageView) findViewById(R.id.EditTwo);
        editThree = (ImageView) findViewById(R.id.EditThree);

    }
}
