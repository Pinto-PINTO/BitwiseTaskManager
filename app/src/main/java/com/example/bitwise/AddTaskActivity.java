package com.example.bitwise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bitwise.Model.TaskModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {

    private EditText addTask;
    private EditText taskTag;
    private Button addTaskButton;

    DatabaseReference databaseUsers;

    String selectedStatus;
    String selectedPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //toolbar back button
        ImageView backButton = findViewById(R.id.toolbar_back_icon);
        TextView toolbarText = findViewById(R.id.toolbar_text);
        toolbarText.setText("Add New Task");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Spinner taskStatusSpinner = findViewById(R.id.taskStatusSpinner);
        Spinner taskPrioritySpinner = findViewById(R.id.taskPrioritySpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_status_array, android.R.layout.simple_spinner_item);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.task_priority_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        taskStatusSpinner.setAdapter(statusAdapter);
        taskPrioritySpinner.setAdapter(priorityAdapter);

        addTask = findViewById(R.id.add_task);
        taskTag = findViewById(R.id.task_tag);
        addTaskButton = findViewById(R.id.add_task_button);
        databaseUsers = FirebaseDatabase.getInstance().getReference();

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        // Task Spinner
        taskStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedStatus = parentView.getItemAtPosition(position).toString();
                Log.d("selectedStatus", selectedStatus);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });

        // Priority Spinner
        taskPrioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedPriority = parentView.getItemAtPosition(position).toString();
                Log.d("selectedPriority", selectedPriority);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing when nothing is selected
            }
        });


    }

    private void insertData(){

        String taskId = databaseUsers.push().getKey();
        String taskName =  addTask.getText().toString();
        String taskTagName = taskTag.getText().toString();
        String taskStatus = selectedStatus;
        String taskPriority = selectedPriority;

        TaskModel taskModel = new TaskModel(taskName,taskPriority,taskStatus,taskTagName);

        databaseUsers.child("tasks").push()
                .setValue(taskModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddTaskActivity.this, "Task Created successfully" , Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddTaskActivity.this, "Task Creation failed" , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll(){
        addTask.setText("");
    }
}