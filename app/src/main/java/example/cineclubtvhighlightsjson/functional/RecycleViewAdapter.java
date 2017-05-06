package example.cineclubtvhighlightsjson.functional;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import example.cineclubtvhighlightsjson.R;
import example.cineclubtvhighlightsjson.entities.TVHighlight;

/**
 * Created by mlu on 06.05.2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private List<TVHighlight> tvHighlights = new LinkedList<>();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView originalTitle;
        public TextView time;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_highlight_title);
            originalTitle = (TextView) v.findViewById(R.id.tv_highlight_original_title);
            time = (TextView) v.findViewById(R.id.tv_highlight_time);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleViewAdapter(List<TVHighlight> tvHighlights) {
        for(int i = 0; i < tvHighlights.size(); i++){
            TVHighlight currentTVHighlight = tvHighlights.get(i);
            boolean condition = DateTimeHelper.isToday(currentTVHighlight.getDateTime());
            if(condition){
                this.tvHighlights.add( currentTVHighlight );
            }
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tv_highlight_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TVHighlight currentTVHighlight = tvHighlights.get(position);

        // title
        holder.title.setText(currentTVHighlight.getTitle());

        // original title
        if(    ! currentTVHighlight.getOriginalTitle().isEmpty()
            && ! currentTVHighlight.getOriginalTitle().equals(currentTVHighlight.getTitle()) ){
            holder.originalTitle.setText("aka '" + currentTVHighlight.getOriginalTitle() + "'");
            holder.originalTitle.setVisibility(View.VISIBLE);
        }
        else{
            holder.originalTitle.setVisibility(View.GONE);
        }

        // dateTime
        Calendar dateTime = currentTVHighlight.getDateTime();
        holder.time.setText( timeFormat.format( dateTime.getTime() ) );
        // dateTime.get( Calendar.HOUR_OF_DAY ) + ":" + dateTime.get( Calendar.MINUTE )

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tvHighlights.size();
    }
}
