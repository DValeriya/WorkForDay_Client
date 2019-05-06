package com.example.workforday.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.workforday.R;
import com.example.workforday.models.WorkApplication;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class WorkApplicationListAdapter extends RecyclerView.Adapter<WorkApplicationListAdapter.WorkApplicationViewHolder> {

    private List<WorkApplication> list;
    private Context context;

    public WorkApplicationListAdapter(List<WorkApplication> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_work_application, parent, false);

        WorkApplicationViewHolder viewHolder = new WorkApplicationViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkApplicationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WorkApplicationViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView circleImageView;
        public TextView name;
        public TextView job;
        public TextView description;
        public GridLayout gridLayout;

        public WorkApplicationViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.profile_img);
            name = itemView.findViewById(R.id.name);
            job = itemView.findViewById(R.id.job);
            description = itemView.findViewById(R.id.worker_description);
            gridLayout  = itemView.findViewById(R.id.worker_hastags);
        }
    }
}
