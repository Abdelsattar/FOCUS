package abdelsattar.com.focus.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import abdelsattar.com.focus.R;

/**
 * Created by lenovo on 20/03/2016.
 */
public class ListViewCheckBoxAdapter extends BaseAdapter {

    ArrayList<String> tasks;
    Activity context;

    public ListViewCheckBoxAdapter(Activity context, ArrayList<String> tasks) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if (view == null) {

            view = LayoutInflater.from(context).inflate(R.layout.checkbox_list_item, viewGroup, false);

        }
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox.setText(tasks.get(position));

        return null;
    }
}
