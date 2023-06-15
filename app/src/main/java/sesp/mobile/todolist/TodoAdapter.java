package sesp.mobile.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter {


    Context ctx;
    private ArrayList<Todo> todos;

    public TodoAdapter(Context ctx, ArrayList<Todo> todos) {
        this.ctx = ctx;
        this.todos = todos;
    }

    class VHTodo extends RecyclerView.ViewHolder{
        private TextView tvActivity, tvTime;

        public VHTodo(View rowView) {
            super(rowView);
            this.tvActivity = rowView.findViewById(R.id.tvActivity);
            this.tvTime = rowView.findViewById(R.id.tvTime);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.todo, parent, false);
        return new VHTodo(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHTodo vh = (VHTodo) holder;
        Todo t = this.todos.get(position);
        vh.tvActivity.setText(t.activity);
        vh.tvTime.setText(t.time);
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }
}
