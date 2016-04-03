package abdelsattar.com.focus.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import abdelsattar.com.focus.DataBase.DatabaseHelper;
import abdelsattar.com.focus.Model.SubTask;
import abdelsattar.com.focus.R;

/**
 * Created by lenovo on 20/03/2016.
 */
public class RecycleViewAdapterSubTasks extends  RecyclerView.Adapter<RecycleViewAdapterSubTasks.PlaceViewHolder> {

    ArrayList<SubTask> subTasks;
    Context context;

    public RecycleViewAdapterSubTasks(Context context, ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
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

        holder.taskName.setText(subTasks.get(position).getSubTask());
//        holder.taskName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, subtasksDetails.class);
//                intent.putExtra("parent_id",subTasks.get(position).getId());
//                context.startActivity(intent);
//
//            }
//        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DatabaseHelper DB = new DatabaseHelper(context);
                DB.deleteSubTask(subTasks.get(position).getId());
                subTasks.remove(position);

                Toast.makeText(context,
                        "Hello Checked",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        CheckBox checkBox;


        PlaceViewHolder(View itemView) {
            super(itemView);
            taskName = (TextView)itemView.findViewById(R.id.ListItem_TaskTV);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
