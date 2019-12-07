package com.example.mydiary.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mydiary.R;

import java.util.List;



/*
 * @author sam
 * @date 19-12-7 下午3:52
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<Diary> diaryList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView time;

        public ViewHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.diary_title);
            time = (TextView)view.findViewById(R.id.diary_time);
        }

    }

    public DiaryAdapter(List<Diary> diaryList){
        this.diaryList = diaryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diary_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diary diary = diaryList.get(position);
        holder.title.setText(diary.getTitle());
        holder.time.setText(diary.getDate());
    }


    @Override
    public int getItemCount() {
        return diaryList.size();
    }
}
