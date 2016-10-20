package abdelsattar.com.focusII.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.List;

import abdelsattar.com.focusII.Activties.subtasksDetails;
import abdelsattar.com.focusII.DataBase.DatabaseHelper;
import abdelsattar.com.focusII.Model.ThankfulFor;
import abdelsattar.com.focusII.R;

/**
 * Created by lenovo on 20/03/2016.
 */
public class ThankfulRecycleViewAdapter extends RecyclerView.Adapter<ThankfulRecycleViewAdapter.PlaceViewHolder> {

    List<ThankfulFor> ThankfulFor;
    Context context;
    DatabaseHelper DB;

    public ThankfulRecycleViewAdapter(Context context, List<ThankfulFor> ThankfulFor) {
        this.ThankfulFor = ThankfulFor;
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

        holder.taskName.setText(ThankfulFor.get(position).getThankfulFor());
        holder.taskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, subtasksDetails.class);
                intent.putExtra("parent_id", ThankfulFor.get(position).getId());
//                Log.d("Adapter", ThankfulFor.get(position).getId() + " "
//                        + ThankfulFor.get(position).getThankfulFor() + " " + position);
                context.startActivity(intent);

            }
        });
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showDeleteDialog(position);

            }
        });

        holder.editThankful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupEditThankfulDialog(position);
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

                DB.deleteThankful(ThankfulFor.get(pos).getId());
                ThankfulFor.remove(pos);
                notifyItemRemoved(pos);

                Toast.makeText(context,
                        "Thankful deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

    private void setupEditThankfulDialog(final int num) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_edit_text, null);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context, R.style.InvitationDialog);

        alertDialogBuilder.setTitle("Edit Thankful");
        alertDialogBuilder.setView(promptsView);

        final EditText thankfullET = (EditText) promptsView
                .findViewById(R.id.DialogET);

        thankfullET.setText(ThankfulFor.get(num).getThankfulFor());

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Update",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (thankfullET.getText().toString().trim().equalsIgnoreCase("")) {
                                    thankfullET.setError("This field can not be blank");
                                } else {
                                    String AddedString = thankfullET.getText().toString();
                                    ThankfulFor.get(num).setThankfulFor(AddedString);
                                    notifyItemChanged(num);
                                    DB.updateThankful(ThankfulFor.get(num));
                                    Toast.makeText(context,
                                            "Thankful Updated",
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
        return ThankfulFor.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        CheckBox checkBox;
        ImageView editThankful;

        PlaceViewHolder(View itemView) {
            super(itemView);
            taskName = (TextView) itemView.findViewById(R.id.ListItem_TaskTV);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            editThankful = (ImageView) itemView.findViewById(R.id.ListItem_EditTask);
        }
    }
}
