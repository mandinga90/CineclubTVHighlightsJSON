package example.cineclubtvhighlightsjson.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import example.cineclubtvhighlightsjson.entities.TVHighlight;
import example.cineclubtvhighlightsjson.functional.Consumer;
import example.cineclubtvhighlightsjson.http.RestClient;
import example.cineclubtvhighlightsjson.http.TVHighlightService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetainFragment extends Fragment {

    private TVHighlightService service = RestClient.getInstance().createService(TVHighlightService.class);
    private List<TVHighlight> tvHighlights;
    private Consumer getConsumer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void getTVHighlights(final Consumer<List<TVHighlight>> consumer) {
        getConsumer = consumer;
        if (tvHighlights == null) {
            Call<List<TVHighlight>> call = service.getTVHighlights();
            call.enqueue(new Callback<List<TVHighlight>>() {
                @Override
                public void onResponse(Call<List<TVHighlight>> call, Response<List<TVHighlight>> response) {
                    if (response.isSuccessful()) {
                        tvHighlights = response.body();
                        getConsumer.apply(tvHighlights);
                    } else {
                        showNetError(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<TVHighlight>> call, Throwable t) {
                    showNetError(t.getMessage());
                }
            });
        } else {
            getConsumer.apply(tvHighlights);
        }
    }

    private void showNetError(String errorMessage){
        Toast.makeText(getActivity(), "Network error!", Toast.LENGTH_SHORT).show();
        Log.d("NetworkError", errorMessage);
    }

}
