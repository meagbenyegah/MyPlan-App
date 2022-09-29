package com.example.myplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myplan.pojo.Plan;
import com.example.myplan.utils.DbHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlansActivity extends AppCompatActivity {


    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private PlanListAdapter adapter;
    private List<Plan> listData;

    private DbHandler dbHandler;
    boolean coldStart = true;

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

        dbHandler = new DbHandler(this);

        listData = dbHandler.getPlans(100);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new PlanListAdapter(listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        if(!coldStart){
            listData.clear();
            listData.addAll(dbHandler.getPlans(100));
            adapter.notifyDataSetChanged();

            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            layoutManager.scrollToPositionWithOffset(0, 0);
        }else{
            coldStart = false;
        }

        super.onResume();
    }
}