package fetch.displaylist;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://fetch-hiring.s3.amazonaws.com/") // Base URL
                    .addConverterFactory(GsonConverterFactory.create()) // JSON converter
                    .build();
        }
        return retrofit;
    }
}

