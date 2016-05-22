package abdelsattar.com.focus.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import abdelsattar.com.focus.Activties.subtasksDetails;
import abdelsattar.com.focus.DataBase.DatabaseHelper;
import abdelsattar.com.focus.Model.Task;
import abdelsattar.com.focus.R;

/**
 * Created by lenovo on 20/03/2016.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.PlaceViewHolder> {

    List<Task> tasks;
    Context context;

    public RecycleViewAdapter(Context context, List<Task> places) {
        this.tasks = places;
        this.context = context;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkbox_list_item, viewGroup, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(v);
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, final int position) {

        holder.taskName.setText(tasks.get(position).getTask());
        holder.taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, subtasksDetails.class);
                intent.putExtra("parent_id", tasks.get(position).getId());
                Log.d("Adapter", tasks.get(position).getId() + " " + tasks.get(position).getTask() + " " + position);
                context.startActivity(intent);

            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    setup(position);
            }
        });

    }

    private void setup(final int pos) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Delete Task!! ");
        builder.setMessage("this will delete this task and all its subtasks");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DatabaseHelper DB = new DatabaseHelper(context);
                DB.deleteTask(tasks.get(pos).getId());
                tasks.remove(pos);
                notifyItemRemoved(pos);
                Toast.makeText(context,
                        "Task deleted, Refresh to see",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        CheckBox checkBox;


        PlaceViewHolder(View itemView) {
            super(itemView);
            taskName = (TextView) itemView.findViewById(R.id.ListItem_TaskTV);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
