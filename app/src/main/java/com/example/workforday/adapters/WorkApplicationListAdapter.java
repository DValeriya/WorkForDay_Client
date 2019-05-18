package com.example.workforday.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.workforday.R;
import com.example.workforday.models.HashTag;
import com.example.workforday.models.WorkApplication;
import com.example.workforday.retrofit.WorkForDayAPI;
import com.example.workforday.retrofit.WorkForDayREST;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.gujun.android.taggroup.TagGroup;
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
        List<String> hashtags = new ArrayList<>();
        for (HashTag hashtag : list.get(position).getHashTags()) {
            hashtags.add(hashtag.getName());
        }
        holder.hashtags.setTags(hashtags);


        if (position == (this.page + 1) * this.RESULT - 2){
            WorkForDayREST rest = WorkForDayAPI.getRest(context);
            rest.getWorkApplications(page++, RESULT).enqueue(new Callback<List<WorkApplication>>() {
                @Override
                public void onResponse(Call<List<WorkApplication>> call, Response<List<WorkApplication>> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            list.addAll(response.body());
                            notifyDataSetChanged();
                        }
                    }
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

        CircleImageView photo;
        TextView name;
        TextView description;
        TagGroup hashtags;

        WorkApplicationViewHolder(@NonNull View itemView) {
            super(itemView);

            photo = itemView.findViewById(R.id.profile_img);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.add_work_line1);
            hashtags = itemView.findViewById(R.id.worker_hashtags);
        }
    }
}
