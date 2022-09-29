package com.example.myplan;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplan.pojo.Plan;

import org.w3c.dom.Text;

import java.util.List;

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.ViewHolder>{
    private List<Plan> plans;

    // RecyclerView recyclerView;
    public PlanListAdapter(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.plan_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Plan plan = plans.get(position);
        holder.title.setText(plans.get(position).getTitle());
        holder.description.setText(plans.get(position).getDescription());
        holder.dateview.setText(""+plans.get(position).getTaskDate());
        holder.timeview.setText(plans.get(position).getTime());
        holder.recurring.setText(""+plans.get(position).isRecurring());

        holder.linearLayoutCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Title: "+plan.getTitle()+" Desc:"+plan.getDescription(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView dateview;
        public TextView timeview;
        public TextView recurring;
        public LinearLayoutCompat linearLayoutCompat;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.dateview = (TextView) itemView.findViewById(R.id.plandate);
            this.timeview = (TextView) itemView.findViewById(R.id.plantime);
            this.recurring = (TextView) itemView.findViewById(R.id.recurring);
            linearLayoutCompat = (LinearLayoutCompat) itemView.findViewById(R.id.linearlayout);
        }
    }
}
