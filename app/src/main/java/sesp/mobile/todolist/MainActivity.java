package sesp.mobile.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Todo> todoList = new ArrayList<>();
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rvTodo);
        ArrayList<Todo> todos = new ArrayList<>();
        RecyclerView rvTodo = this.findViewById(R.id.rvTodo);
           Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://mgm.ub.ac.id/todo.php";

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject responseObj = response.getJSONObject(i);

                                String id = responseObj.getString("id");
                                String activity = responseObj.getString("what");
                                String time = responseObj.getString("time");
                                todos.add(new Todo(id, activity, time));

//                                handler.post(new Runnable() {
//                                    @Override
//                                    public void run() {
                                        TodoAdapter tAdapter = new TodoAdapter(MainActivity.this, todos);
                                        rvTodo.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        rvTodo.setAdapter(tAdapter);
//                                    }
//                                });

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Gagal memuat data..", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsonArrayRequest);
            }

        });
        t.start();
    }
}