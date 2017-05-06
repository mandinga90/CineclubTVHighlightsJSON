package example.cineclubtvhighlightsjson.http;

import java.util.List;

import example.cineclubtvhighlightsjson.entities.TvHighlight;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TVHighlightService {

    @GET("/mitarbeiter-seite/tv1.json.php")
//    @GET("/data.json")
//    @GET("/rss/tv.json")
    Call<List<TvHighlight>> getTVHighlights();

}
