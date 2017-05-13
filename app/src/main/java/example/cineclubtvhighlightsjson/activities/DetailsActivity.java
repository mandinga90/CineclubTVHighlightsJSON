package example.cineclubtvhighlightsjson.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import example.cineclubtvhighlightsjson.R;
import example.cineclubtvhighlightsjson.databinding.TvHighlightDetailsBinding;
import example.cineclubtvhighlightsjson.entities.TvHighlight;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TvHighlight tvHighlightForDetails = getIntent().getParcelableExtra(getResources().getString(R.string.parcelableExtra));
        TvHighlightDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.tv_highlight_details);
        binding.setTvHighlight(tvHighlightForDetails);
        tvHighlightForDetails.setCoverImage(binding.tvHighlightDetailsCover);
        binding.tvHighlightDetailsChannelIcon.setImageResource(tvHighlightForDetails.getTvChannelIcon());

        // original title
        String originalTitleToBeDisplayed = tvHighlightForDetails.getOriginalTitleToBeDisplayed();
        if( ! originalTitleToBeDisplayed.isEmpty() ){
            binding.tvHighlightDetailsOriginalTitle.setText(originalTitleToBeDisplayed);
            binding.tvHighlightDetailsOriginalTitle.setVisibility(View.VISIBLE);
        }
        else{
            binding.tvHighlightDetailsOriginalTitle.setVisibility(View.GONE);
        }

        // dateTime
        binding.tvHighlightDetailsTime.setText( tvHighlightForDetails.getDateTimeString() );

        // advertising in minutes
        binding.tvHighlightDetailsAdvertisingInMinutes.setText(tvHighlightForDetails.getAdvertisingInMinutesText());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
