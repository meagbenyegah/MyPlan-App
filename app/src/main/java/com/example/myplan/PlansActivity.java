package com.example.myplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myplan.pojo.Plan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlansActivity extends AppCompatActivity {


    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private PlanListAdapter adapter;
    private List<Plan> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(PlansActivity.this, AddPlan.class));
            }
        });

        listData = new ArrayList<>();
        listData.add(new Plan("Daily Standup", "Agile Update", new Date(), "10:15am", true));
        listData.add(new Plan("Lunch", "General Eating", new Date(), "12:15am", true));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new PlanListAdapter(listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}