package abdelsattar.com.focusII.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import abdelsattar.com.focusII.DataBase.DatabaseHelper;
import abdelsattar.com.focusII.Model.SubTask;
import abdelsattar.com.focusII.R;

/**
 * Created by lenovo on 20/03/2016.
 */
public class RecycleViewAdapterSubTasks extends RecyclerView.Adapter<RecycleViewAdapterSubTasks.PlaceViewHolder> {

    ArrayList<SubTask> subTasks;
    Context context;
    DatabaseHelper DB;

    public RecycleViewAdapterSubTasks(Context context, ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
        this.context = context;

        DB = new DatabaseHelper(context);
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

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showDeleteDialog(position);
            }
        });

        holder.editSubTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupAddTaskDialog(position);
            }
        });


    }
    private void showDeleteDialog(final int pos) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Delete Sub-Task!! ");
        builder.setMessage("Do you want delete this sub-task?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DB.deleteSubTask(subTasks.get(pos).getId());
                subTasks.remove(pos);
                notifyItemRemoved(pos);

                Toast.makeText(context,
                        "Sub-task deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

    private void setupAddTaskDialog(final int num) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_edit_text, null);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context, R.style.InvitationDialog);

        alertDialogBuilder.setTitle("Edit Sub-Task");
        alertDialogBuilder.setView(promptsView);

        final EditText subTaskET = (EditText) promptsView
                .findViewById(R.id.DialogET);

        subTaskET.setText(subTasks.get(num).getSubTask());

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (subTaskET.getText().toString().trim().equalsIgnoreCase("")) {
                                    subTaskET.setError("This field can not be blank");
                                } else {
                                    String AddedString = subTaskET.getText().toString();
                                    subTasks.get(num).setSubTask(AddedString);
                                    notifyItemChanged(num);
                                    DB.updateSubTask(subTasks.get(num));
                                    Toast.makeText(context,
                                            "Sub-task Updated",
                                            Toast.LENGTH_SHORT).show();
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
        ImageView editSubTask;

        PlaceViewHolder(View itemView) {
            super(itemView);
            taskName = (TextView) itemView.findViewById(R.id.ListItem_TaskTV);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            editSubTask = (ImageView) itemView.findViewById(R.id.ListItem_EditTask);
        }
    }
}
