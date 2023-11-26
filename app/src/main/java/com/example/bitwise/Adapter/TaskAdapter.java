package com.example.bitwise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bitwise.Model.TaskModel;
import com.example.bitwise.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {



    Context context;
    ArrayList<TaskModel> list;

    DatabaseReference databaseReference;

    public TaskAdapter(Context context, ArrayList<TaskModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.taskentry, parent, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("tasks");
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder holder, int position) {
        TaskModel taskModel = list.get(position);
        holder.taskName.setText(taskModel.getTaskName());
        holder.taskPriority.setText(taskModel.getTaskPriority());
        holder.taskStatus.setText(taskModel.getTaskStatus());
        holder.taskTagName.setText(taskModel.getTaskTag());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String itemKey = taskModel.getKey(); // Assuming TaskModel has a getKey() method

                // Delete the item from Firebase using its key
                deleteItemFromFirebase(itemKey, position);
            }
        });


    }

    private void deleteItemFromFirebase(String itemKey, int position) {
        databaseReference.child(itemKey).removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Deletion successful
                        list.remove(position);
                        notifyItemRemoved(position);
                    } else {
                        // Deletion failed
                        // Handle the error
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView taskName, taskTagName, taskStatus, taskPriority;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView ){
            super(itemView);
            taskName = itemView.findViewById(R.id.taskName);
            taskPriority = itemView.findViewById(R.id.taskPriotity);
            taskStatus = itemView.findViewById(R.id.taskStatus);
            taskTagName = itemView.findViewById(R.id.taskTag);
            checkBox = itemView.findViewById(R.id.todoCheckBox);


        }
    }
}
