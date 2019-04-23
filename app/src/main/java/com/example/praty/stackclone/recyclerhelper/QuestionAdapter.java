package com.example.praty.stackclone.recyclerhelper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.praty.stackclone.R;
import com.example.praty.stackclone.model.Questions;
import com.example.praty.stackclone.model.QuestionsTitle;

import java.util.List;

//adapter for recyclerview and QuestionsTitle
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<QuestionsTitle> mQuestions;
    private String mTag;
    private ItemClickListener mClickListener;

    public QuestionAdapter(List<QuestionsTitle> mQuestions, String mTag, ItemClickListener mClickListener) {
        this.mQuestions = mQuestions;
        this.mTag = mTag;
        this.mClickListener = mClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.questionrow,parent,false);
        final ViewHolder holder=new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String newTag="#"+mTag;
        holder.tagView.setText(newTag);
        holder.questionView.setText(mQuestions.get(position).getQuestionTitle());
    }


    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionView, tagView;
        public ViewHolder(View itemView) {
            super(itemView);
            questionView=(TextView) itemView.findViewById(R.id.questionView);
            tagView=(TextView) itemView.findViewById(R.id.tagView);
        }
    }
}
