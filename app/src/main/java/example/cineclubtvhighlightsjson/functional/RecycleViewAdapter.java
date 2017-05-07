package example.cineclubtvhighlightsjson.functional;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import example.cineclubtvhighlightsjson.R;
import example.cineclubtvhighlightsjson.entities.TvHighlight;

/**
 * Created by mlu on 06.05.2017.
 * sources:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 * http://www.androidhive.info/2016/01/android-working-with-recycler-view/
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private List<TvHighlight> tvHighlights = new LinkedList<>();
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView originalTitle;
        public TextView time;
        public ImageView tvChannelIcon;
        public TextView advertisingInMinutes;
        public ImageView cover;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_highlight_title);
            originalTitle = (TextView) v.findViewById(R.id.tv_highlight_original_title);
            time = (TextView) v.findViewById(R.id.tv_highlight_time);
            tvChannelIcon = (ImageView) v.findViewById(R.id.tv_highlight_channel_icon);
            advertisingInMinutes = (TextView) v.findViewById(R.id.tv_highlight_advertising_in_minutes);
            cover = (ImageView) v.findViewById(R.id.tv_highlight_cover);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleViewAdapter(List<TvHighlight> tvHighlights) {
        for(int i = 0; i < tvHighlights.size(); i++){
            TvHighlight currentTvHighlight = tvHighlights.get(i);
            boolean condition = DateTimeHelper.isToday(currentTvHighlight.getDateTime());
            if(condition){
                this.tvHighlights.add(currentTvHighlight);
            }
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.constraint_tv_highlight_list, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TvHighlight currentTvHighlight = tvHighlights.get(position);

        // title
        String title = "";
        title = currentTvHighlight.getTitle();

        // release year
        if( currentTvHighlight.getReleaseYear() != null ){
            int releaseYear = currentTvHighlight.getReleaseYear().get( Calendar.YEAR );
            title += " (" + releaseYear + ")";
        }
        holder.title.setText( title );

        // original title
        if(    ! currentTvHighlight.getOriginalTitle().isEmpty()
            && ! currentTvHighlight.getOriginalTitle().equals(currentTvHighlight.getTitle()) ){
            holder.originalTitle.setText("aka '" + currentTvHighlight.getOriginalTitle() + "'");
            holder.originalTitle.setVisibility(View.VISIBLE);
        }
        else{
            holder.originalTitle.setVisibility(View.GONE);
        }

        // dateTime
        Calendar dateTime = currentTvHighlight.getDateTime();
        holder.time.setText( timeFormat.format( dateTime.getTime() ) );

        // tv channel icon
        int tvChannelIcon = currentTvHighlight.getTvChannelIcon();
        if( tvChannelIcon > 0 ){
            holder.tvChannelIcon.setImageResource(tvChannelIcon);
        }

        // advertising in minutes
        int advertisingInMinutes = currentTvHighlight.getAdvertisingInMinutes();
        StringBuilder advertisingInMinutesTextBuilder = new StringBuilder();
        if( advertisingInMinutes > 0 ){
            advertisingInMinutesTextBuilder.append(advertisingInMinutes);
            advertisingInMinutesTextBuilder.append(" Min. ");
        }
        else{
            advertisingInMinutesTextBuilder.append("Keine ");
        }
        advertisingInMinutesTextBuilder.append("Werbung");
        holder.advertisingInMinutes.setText(advertisingInMinutesTextBuilder.toString());

        // cover
        currentTvHighlight.setCoverImage(holder.cover);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tvHighlights.size();
    }
}
