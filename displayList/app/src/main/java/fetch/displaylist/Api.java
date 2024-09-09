package fetch.displaylist;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("hiring.json")
    Call<List<Item>> getItems();
}
