package com.redteam.workforday.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.redteam.workforday.R;
import com.redteam.workforday.models.WorkApplication;
import com.redteam.workforday.retrofit.WorkForDayAPI;
import com.redteam.workforday.retrofit.WorkForDayREST;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkApplicationListAdapter extends
        RecyclerView.Adapter<WorkApplicationListAdapter.WorkApplicationViewHolder> {

    private List<WorkApplication> list;
    private Context context;
    private int page;
    private final int RESULT;

    public WorkApplicationListAdapter(List<WorkApplication> list, Context context) {
        this.list = list;
        this.context = context;
        this.RESULT = context.getResources().getInteger(R.integer.conut_of_results_for_page);
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

        holder.name.setText(list.get(position).getUser().getName());
        holder.description.setText(list.get(position).getDescription());
       // holder.photo.setImageBitmap(list.get(position).getUser().getPhoto());

        for (int i = 0; i < 6 && i < list.get(position).getHashTags().size(); i++){
            TextView textView = new TextView(context);
            textView.setText(list.get(position).getHashTags().get(i).getName());
            holder.gridLayout.addView(textView);
        }

        if (position == (this.page + 1) * this.RESULT - 2){
            WorkForDayREST rest = WorkForDayAPI.getRest(context);
            rest.getWorkApplications(page++, RESULT).enqueue(new Callback<List<WorkApplication>>() {
                @Override
                public void onResponse(Call<List<WorkApplication>> call, Response<List<WorkApplication>> response) {
                    list.addAll(response.body());
                    notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<WorkApplication>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class WorkApplicationViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView photo;
        public TextView name;
        public TextView description;
        public GridLayout gridLayout;

        public WorkApplicationViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.profile_img);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.worker_description);
            gridLayout = itemView.findViewById(R.id.worker_hastags);
        }
    }
}
