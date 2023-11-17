package com.example.bitwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitwise.Model.TaskModel;
import com.example.bitwise.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    Context context;
    ArrayList<TaskModel> list;

    public TaskAdapter(Context context, ArrayList<TaskModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.taskentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder holder, int position) {
        TaskModel taskModel = list.get(position);
        holder.taskName.setText(taskModel.getTaskName());
        holder.taskPriority.setText(taskModel.getTaskPriority());
        holder.taskStatus.setText(taskModel.getTaskStatus());
        holder.taskTagName.setText(taskModel.getTaskTag());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView taskName, taskTagName, taskStatus, taskPriority;
        public MyViewHolder(@NonNull View itemView ){
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskPriority = itemView.findViewById(R.id.taskPriotity);
            taskStatus = itemView.findViewById(R.id.taskStatus);
            taskTagName = itemView.findViewById(R.id.taskTag);

        }
    }
}
