package fetch.displaylist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> displayList;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        displayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        listView.setAdapter(adapter);

        fetchAndListItems();
    }

    private void fetchAndListItems() {
        Api api = RetrofitClient.getClient().create(Api.class);
        Call<List<Item>> call = api.getItems();
        call.enqueue(new Callback<List<Item>>() {
                         @Override
                         public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                             if (response.isSuccessful() && response.body() != null) {
                                 List<Item> items = response.body();

                                 Map<Integer, List<String>> groupById = new HashMap<>();
                                 for (Item item : items) {
                                     if (item.getName() == null || item.getName().trim().isEmpty()) {
                                         continue;
                                     }
                                     if (!groupById.containsKey(item.getListId())) {
                                         groupById.put(item.getListId(), new ArrayList<>());
                                     }
                                     groupById.get(item.getListId()).add(item.getName());
                                 }

                                 List<Map.Entry<Integer, List<String>>> sortedGroup = new ArrayList<>(groupById.entrySet());
                                 Collections.sort(sortedGroup, Map.Entry.comparingByKey());

                                 for (Map.Entry<Integer, List<String>> entry : sortedGroup) {
                                     int listId = entry.getKey();
                                     List<String> names = entry.getValue();
                                     Collections.sort(names, (a, b) -> {
                                         return Integer.parseInt(a.substring(5)) - Integer.parseInt(b.substring(5));
                                     });

                                     for (String name:names) {
                                         displayList.add("listID: " + listId + " name: " + name);
                                     }
                                 }

                                 adapter.notifyDataSetChanged();

                             }

                         }

                         @Override
                         public void onFailure(Call<List<Item>> call, Throwable t) {

                         }
                     }

        );

    }

}