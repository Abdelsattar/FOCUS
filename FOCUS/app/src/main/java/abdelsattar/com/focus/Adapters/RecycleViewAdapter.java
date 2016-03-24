package abdelsattar.com.focus.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import abdelsattar.com.focus.R;

/**
 * Created by lenovo on 20/03/2016.
 */
public class RecycleViewAdapter extends  RecyclerView.Adapter<RecycleViewAdapter.PlaceViewHolder> {

    List<String> places;

    public RecycleViewAdapter(List<String> places) {
        this.places = places;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.checkbox_list_item, viewGroup, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(v);
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {

        holder.taskName.setText(places.get(position));

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;

        PlaceViewHolder(View itemView) {
            super(itemView);
            taskName = (TextView)itemView.findViewById(R.id.ListItem_TaskTV);
        }
    }
}
