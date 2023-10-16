package com.example.bitwise.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bitwise.MainActivity;
import com.example.bitwise.Model.ToDoModel;
import com.example.bitwise.R;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder>{
    private List<ToDoModel> todoList;

    private MainActivity activity;

    public ToDoAdapter(MainActivity activity) {
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(itemView);
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
//        db.openDatabase();

        ToDoModel item = todoList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
//        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    db.updateStatus(item.getId(), 1);
//                } else {
//                    db.updateStatus(item.getId(), 0);
//                }
//            }
//        });
    }

    public int getItemCount() {
        return todoList.size();
    }

//    public Context getContext() {
//        return activity;
//    }


    private boolean toBoolean(int n) {
        return n!=0;
    }

    public void setTasks(List<ToDoModel> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox task;

        ViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.todoCheckBox);
        }
    }
















}
